package ru.itis.conference.model.room;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.conference.model.Schedule;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private RoomName name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "room")
    private Schedule schedule;

}
