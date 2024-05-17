package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.CreateResponseDto;
import com.sparta.scheduler.dto.ScheduleRequestDto;
import com.sparta.scheduler.dto.ScheduleResponseDto;
import com.sparta.scheduler.dto.UpdateResponseDto;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public CreateResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> CreateResponseDto
        CreateResponseDto createResponseDto = new CreateResponseDto(schedule);

        return createResponseDto;
    }

    public ScheduleResponseDto getChooseSchedule(Long id) {
        // DB 조회
        return scheduleRepository.findSchedule(id);
    }

    public List<ScheduleResponseDto> getSchedule() {
        // DB 조회
        return scheduleRepository.findAll();
    }

    public UpdateResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인, 비밀번호 일치 확인
        Schedule schedule = scheduleRepository.findByIdAndPw(id, requestDto.getPassword());
        if(schedule != null) {
            // schedule 내용 수정
            return scheduleRepository.update(id, requestDto, schedule);

        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않거나 비밀번호가 일치하지 않습니다.");
        }
    }

    public Long deleteSchedule(Long id, Integer password) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = scheduleRepository.findByIdAndPw(id, password);
        if(schedule != null) {
            // schedule 삭제
            scheduleRepository.delete(id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않거나 비밀번호가 일치하지 않습니다..");
        }
    }
}
