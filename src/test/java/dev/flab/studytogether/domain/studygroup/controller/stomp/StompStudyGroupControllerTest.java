package dev.flab.studytogether.domain.studygroup.controller.stomp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import dev.flab.studytogether.domain.studygroup.entity.ParticipantV2;
import dev.flab.studytogether.domain.studygroup.entity.StudyGroup;
import dev.flab.studytogether.domain.studygroup.repository.StudyGroupRepository;
import dev.flab.studytogether.domain.studygroup.service.StudyGroupJoinService;
import dev.flab.studytogether.util.TestFixtureUtils;
import dev.flab.studytogether.websocket.ServerMessagePayload;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StompStudyGroupControllerTest {
    @LocalServerPort
    private int port;
    private String url;
    private ObjectMapper objectMapper;
    private static final String END_POINT_PREFIX = "/study-group";
    @Autowired
    private StudyGroupJoinService studyGroupJoinService;
    @Autowired
    private StudyGroupRepository studyGroupRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @BeforeEach
    void setup() {
        this.url = "ws://localhost:" + port + END_POINT_PREFIX;

        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        this.objectMapper = messageConverter.getObjectMapper();
        this.objectMapper.registerModule(new ParameterNamesModule());
    }

    @Test
    @DisplayName("동시 입장 처리 테스트")
    void concurrentJoinHandleTest() throws InterruptedException {
        // given
        StudyGroup studyGroup = TestFixtureUtils.randomStudyGroup();
        studyGroupRepository.save(studyGroup);
        int memberCount = 30;

        ExecutorService executorService = Executors.newFixedThreadPool(memberCount);
        CountDownLatch latch = new CountDownLatch(memberCount);

        // when
        AtomicLong memberId = new AtomicLong(1L);
        for (int i = 0; i < memberCount; i++) {
            executorService.submit(() -> {
                try {
                    long currentMemberId = memberId.getAndIncrement();

                    ParticipantV2 participant = studyGroupJoinService.joinGroup(studyGroup.getId(), currentMemberId);

                    if(participant.getId() != null) {
                        // 개별 WebSocket 세션 생성
                        WebSocketStompClient stompClient =
                                new WebSocketStompClient(new SockJsClient(createTransportClient()));
                        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

                        StompSession session = stompClient.connect(url,
                                        new StompSessionHandlerAdapter() {})
                                .get(3, TimeUnit.SECONDS);

                        CompletableFuture<ServerMessagePayload> completableFuture = new CompletableFuture<>();


                        // WebSocket 구독 설정
                        session.subscribe("/subscribe/queue/group" + studyGroup.getId(), new StompFrameHandler() {
                            @Override
                            public Type getPayloadType(StompHeaders headers) {
                                return ServerMessagePayload.class;
                            }

                            @Override
                            public void handleFrame(StompHeaders headers, Object payload) {
                                completableFuture.complete((ServerMessagePayload) payload);
                            }
                        });


                        // WebSocket 메시지 전송
                        String payload = objectMapper.writeValueAsString(Map.of(
                                "type", "JOIN",
                                "groupId", studyGroup.getId().toString(),
                                "participantId", participant.getId().toString()
                        ));
                        session.send("/publish/study-group", payload);

                        // then: WebSocket 응답 검증
                        ServerMessagePayload response = completableFuture.get(5, TimeUnit.SECONDS);
                        assertNotNull(response);
                        log.debug("Response : {}", response);

                        // 웹소켓 세션 종료
                        session.disconnect();
                    }

                } catch (Exception e) {
                    System.err.println("에러: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();
    }

    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }
}
