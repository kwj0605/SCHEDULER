package com.sparta.scheduler.entity;

import com.sparta.scheduler.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;
    private String toDoTitle;
    private String whatToDo;
    private String manager;
    private Integer password;
    private LocalDate dateCreated;

    public Schedule(ScheduleRequestDto requestDto) {
        this.toDoTitle = requestDto.getToDoTitle();
        this.whatToDo = requestDto.getWhatToDo();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
        this.dateCreated = LocalDate.now();
    }
}
