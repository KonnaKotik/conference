package ru.itis.conference.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.conference.model.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_id")
    private Long id;

    private String name;

    private String description;

    private String link;

    @ManyToMany(mappedBy = "talks")
    private List<User> speakers;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "talk")
    private Schedule schedule;


}
