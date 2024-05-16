package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String toDoTitle;
    private String whatToDo;
    private String manager;
    private Integer password;
    private LocalDate dateCreated;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.toDoTitle = schedule.getToDoTitle();
        this.whatToDo = schedule.getWhatToDo();
        this.manager = schedule.getManager();
        this.password = schedule.getPassword();
        this.dateCreated = schedule.getDateCreated();
    }

    public ScheduleResponseDto(Long id, String toDoTitle, String whatToDo, String manager, Integer password, LocalDate dateCreated) {
        this.id = id;
        this.toDoTitle = toDoTitle;
        this.whatToDo = whatToDo;
        this.manager = manager;
        this.password = password;
        this.dateCreated = dateCreated;
    }
}
