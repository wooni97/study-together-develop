package dev.flab.studytogether.domain.event.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.flab.studytogether.domain.event.DomainEvent;
import dev.flab.studytogether.domain.event.infrastructure.rowmapper.DomainEventRowMapper;
import org.springframework.stereotype.Component;
import org.reflections.Reflections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EventMapper {
    private Map<Class<? extends DomainEvent>, Class<? extends DomainEventRowMapper>> typeMap = new HashMap<>();

    public EventMapper() {
        this("dev.flab.studytogether");
    }

    public EventMapper(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<? extends DomainEvent>> eventClasses = reflections.getSubTypesOf(DomainEvent.class);
        Set<Class<? extends DomainEventRowMapper>> rowClasses = reflections.getSubTypesOf(DomainEventRowMapper.class);

        typeMap = rowClasses
                .stream()
                .collect(Collectors.toMap(rowClass -> {
                    try {
                        return rowClass.getDeclaredConstructor().newInstance().eventType();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }}, rowClass -> rowClass));

        if(!typeMap.keySet().containsAll(eventClasses))
            throw new RuntimeException(new ClassNotFoundException());
    }

    public DomainEvent eventOf(String json, String eventTypeValue) {
        Class<?> clazz;
        try {
            clazz = Class.forName(eventTypeValue);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(!DomainEvent.class.isAssignableFrom(clazz)) throw new ClassCastException();
        Class<? extends DomainEvent> eventType = clazz.asSubclass(DomainEvent.class);
        Class<? extends DomainEventRowMapper> rowMapperType = rowMapperFrom(eventType);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            return objectMapper.readValue(json, rowMapperType).createDomainEvent();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Class<? extends DomainEventRowMapper> rowMapperFrom(Class<? extends DomainEvent> eventType) {
        Class<? extends DomainEventRowMapper> type = typeMap.get(eventType);
        if(type == null) throw new RuntimeException(new ClassNotFoundException("not found class : " + eventType));
        return type;
    }
}
