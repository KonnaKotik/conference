package ru.itis.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.conference.model.Talk;
import ru.itis.conference.model.user.User;

import java.util.List;
import java.util.Optional;

public interface TalkRepository extends JpaRepository<Talk, Long> {

    List<Talk> findAllBySpeakersId(Long id);

    Optional<Talk> findByName(String name);

    boolean existsByName(String Name);
}
