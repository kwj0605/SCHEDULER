package com.sparta.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class ScheduleRequestDto {
    private String toDoTitle;
    private String whatToDo;
    private String manager;
    private Integer password;
}
