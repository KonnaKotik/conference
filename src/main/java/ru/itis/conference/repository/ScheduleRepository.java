package ru.itis.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.conference.model.Schedule;
import ru.itis.conference.model.Talk;
import ru.itis.conference.model.room.Room;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByRoom(Room room);

    boolean existsByDateStartAndRoom(Date dateStart, Room room);

    List<Schedule> findAllByRoom(Room room);
}
