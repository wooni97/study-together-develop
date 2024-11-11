package dev.flab.studytogether.domain.event.infrastructure;

import dev.flab.studytogether.domain.event.EventPublish;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class EventConsumer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final JdbcTemplate jdbcTemplate;
    private final EventMapper eventMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final EventPublish eventPublish;

    @Scheduled(fixedDelay = 3000)
    public void consume() {
        List<EventDto> eventDtos = findAll();

        List<Long> ids = new ArrayList<>();
        eventDtos.forEach(eventDto -> {
            try {
                eventPublish.publish(eventDto.getEvent());
                ids.add(eventDto.getMetaData().getEventId());
            } catch (RuntimeException e) {
                log.error("id : {} message : {}", eventDto.getMetaData().getEventId(), e.getMessage());
            }
        });

        deleteProcessedEvents(ids);
    }

    public List<EventDto> findAll() {
        String query = "SELECT EVENT_ID, EVENT_TYPE, PAYLOAD, CREATED_AT FROM EVENT";
        return jdbcTemplate.query(query, eventDtoRowMapper());
    }

    public void deleteProcessedEvents(List<Long> processedEventIds) {
        if(processedEventIds.isEmpty()) return;

        String query = "DELETE FROM EVENT WHERE EVENT_ID IN (:ids)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", processedEventIds);

        namedParameterJdbcTemplate.update(query, parameters);
    }

    private RowMapper<EventDto> eventDtoRowMapper() {
        return (rs, rowNum) -> new EventDto(
                new EventMetaData(rs.getLong("EVENT_ID")),
                eventMapper.eventOf(rs.getString("PAYLOAD"), rs.getString("EVENT_TYPE"))
        );
    }
}
