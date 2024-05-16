package com.sparta.scheduler.repository;

import com.sparta.scheduler.dto.ScheduleRequestDto;
import com.sparta.scheduler.dto.ScheduleResponseDto;
import com.sparta.scheduler.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    public Schedule save(Schedule schedule) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (to_do_title, What_to_do, manager, password, Date_Created) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getToDoTitle());
                    preparedStatement.setString(2, schedule.getWhatToDo());
                    preparedStatement.setString(3, schedule.getManager());
                    preparedStatement.setInt(4, schedule.getPassword());
                    preparedStatement.setDate(5, Date.valueOf(schedule.getDateCreated()));
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
    }

    public ScheduleResponseDto findSchedule(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                Long sid = rs.getLong("id");
                String toDoTitle = rs.getString("to_do_title");
                String whatToDo = rs.getString("What_to_do");
                String manager = rs.getString("manager");
                Integer password = rs.getInt("password");
                LocalDate dateCreated = rs.getDate("Date_Created").toLocalDate();
                return new ScheduleResponseDto(sid, toDoTitle, whatToDo, manager, password, dateCreated);
            } else {
                return null;
            }
        }, id);
    }

    public List<ScheduleResponseDto> findAll() {
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 schedule 데이터들을 scheduleResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String toDoTitle = rs.getString("to_do_title");
                String whatToDo = rs.getString("What_to_do");
                String manager = rs.getString("manager");
                Integer password =(rs.getInt("password"));
                LocalDate dateCreated = rs.getDate("Date_Created").toLocalDate();
                return new ScheduleResponseDto(id, toDoTitle, whatToDo, manager, password, dateCreated);
            }
        });
    }

    public void update(Long id, ScheduleRequestDto requestDto, Schedule schedule) {
        String sql = "UPDATE schedule SET to_do_title = ?, What_to_do = ?, manager = ?, password = ?, Date_Created = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getToDoTitle(), requestDto.getWhatToDo(), requestDto.getManager(), requestDto.getPassword(), schedule.getDateCreated(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setToDoTitle(resultSet.getString("to_do_title"));
                schedule.setWhatToDo(resultSet.getString("What_to_do"));
                schedule.setManager(resultSet.getString("manager"));
                schedule.setPassword(resultSet.getInt("password"));
                schedule.setDateCreated(resultSet.getDate("Date_Created").toLocalDate());
                return schedule;
            } else {
                return null;
            }
        }, id);
    }
}
