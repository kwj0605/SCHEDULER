package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.ScheduleRequestDto;
import com.sparta.scheduler.dto.ScheduleResponseDto;
import com.sparta.scheduler.dto.UpdateDto;
import com.sparta.scheduler.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public ScheduleResponseDto schedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto chooseSchedule(@PathVariable Long id) {
        return scheduleService.getChooseSchedule(id);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getschedule() {
        return scheduleService.getSchedule();
    }

    @PutMapping("/schedule/{id}/{password}")
    public UpdateDto updateSchedule(@PathVariable Long id, @PathVariable Integer password, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, password, requestDto);
    }

    @DeleteMapping("/schedule/{id}/{password}")
    public Long deleteSchedule(@PathVariable Long id, @PathVariable Integer password) {
        return scheduleService.deleteSchedule(id, password);
    }
}
