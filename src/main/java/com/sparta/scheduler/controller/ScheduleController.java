package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.CreateViewResponseDto;
import com.sparta.scheduler.dto.ScheduleRequestDto;
import com.sparta.scheduler.dto.UpdateResponseDto;
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
    public CreateViewResponseDto schedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping("/schedule/{id}")
    public CreateViewResponseDto chooseSchedule(@PathVariable Long id) {
        return scheduleService.getChooseSchedule(id);
    }

    @GetMapping("/schedule")
    public List<CreateViewResponseDto> getschedule() {
        return scheduleService.getSchedule();
    }

    @PutMapping("/schedule/{id}")
    public UpdateResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }

    @DeleteMapping("/schedule/{id}")
    public Long deleteSchedule(@PathVariable Long id, @RequestBody Integer password) {
        return scheduleService.deleteSchedule(id, password);
    }
}
