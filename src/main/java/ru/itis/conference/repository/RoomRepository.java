package ru.itis.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.conference.model.room.Room;
import ru.itis.conference.model.room.RoomName;

import java.util.Optional;

public interface RoomRepository  extends JpaRepository<Room, Long> {
    Optional<Room> findByName(RoomName name);
}
