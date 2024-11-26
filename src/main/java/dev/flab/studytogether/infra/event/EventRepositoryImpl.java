package dev.flab.studytogether.infra.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.flab.studytogether.core.domain.event.DomainEvent;
import dev.flab.studytogether.core.domain.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(DomainEvent event) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("EVENT").usingGeneratedKeyColumns("EVENT_ID");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String paylaod = null;
        try {
            paylaod = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("EVENT_TYPE", event.getClass().getName());
        parameters.put("PAYLOAD", paylaod);
        parameters.put("CREATED_AT", event.getCreatedAt());

        jdbcInsert.execute(parameters);
    }
}
