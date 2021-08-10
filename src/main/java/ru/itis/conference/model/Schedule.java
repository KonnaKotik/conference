package ru.itis.conference.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.conference.model.room.Room;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateStart;

    private Date dateEnd;

    @OneToOne
    @JoinColumn(name = "fk_room_id", referencedColumnName = "room_id")
    private Room room;

    @OneToOne
    @JoinColumn(name = "fk_talk_id", referencedColumnName = "talk_id")
    private Talk talk;
}
