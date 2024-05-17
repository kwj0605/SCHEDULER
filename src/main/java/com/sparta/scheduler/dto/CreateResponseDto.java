package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class CreateResponseDto {
    private Long id;
    private String toDoTitle;
    private String whatToDo;
    private String manager;
    private LocalDate dateCreated;

    public CreateResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.toDoTitle = schedule.getToDoTitle();
        this.whatToDo = schedule.getWhatToDo();
        this.manager = schedule.getManager();
        this.dateCreated = schedule.getDateCreated();
    }
}
