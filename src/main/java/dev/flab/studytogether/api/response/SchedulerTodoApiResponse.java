package dev.flab.studytogether.api.response;

import dev.flab.studytogether.api.dto.SchedulerTodoDto;
import lombok.Getter;

@Getter
public class SchedulerTodoApiResponse {
    private final int schedulerSequenceId;
    private final long todoID;
    private final String todoContent;

    private SchedulerTodoApiResponse(int schedulerSequenceId, long todoID, String todoContent) {
        this.schedulerSequenceId = schedulerSequenceId;
        this.todoID = todoID;
        this.todoContent = todoContent;
    }

    public static SchedulerTodoApiResponse from(SchedulerTodoDto schedulerTodoDto) {
        return new SchedulerTodoApiResponse(
                schedulerTodoDto.getSchedulerSequenceID(),
                schedulerTodoDto.getTodoSequenceID(),
                schedulerTodoDto.getTodoContent());
    }
}
