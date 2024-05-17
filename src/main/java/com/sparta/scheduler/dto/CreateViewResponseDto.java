package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class CreateViewResponseDto {
    private Long id;
    private String toDoTitle;
    private String whatToDo;
    private String manager;
    private LocalDate dateCreated;

    public CreateViewResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.toDoTitle = schedule.getToDoTitle();
        this.whatToDo = schedule.getWhatToDo();
        this.manager = schedule.getManager();
        this.dateCreated = schedule.getDateCreated();
    }

    public CreateViewResponseDto(Long id, String toDoTitle, String whatToDo, String manager, LocalDate dateCreated) {
        this.id = id;
        this.toDoTitle = toDoTitle;
        this.whatToDo = whatToDo;
        this.manager = manager;
        this.dateCreated = dateCreated;
    }
}
