package dev.flab.studytogether.core.domain.chat.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
public abstract class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;

    protected Chat() {}

    protected Chat(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
