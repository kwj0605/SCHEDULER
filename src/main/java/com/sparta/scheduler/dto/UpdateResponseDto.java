package com.sparta.scheduler.dto;

import lombok.Getter;

@Getter
public class UpdateResponseDto {
    private String toDoTitle;
    private String whatToDo;
    private String manager;

    public UpdateResponseDto(String toDoTitle, String whatToDo, String manager) {
        this.toDoTitle = toDoTitle;
        this.whatToDo = whatToDo;
        this.manager = manager;
    }

}
