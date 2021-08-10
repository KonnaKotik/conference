package ru.itis.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.conference.model.user.User;
import ru.itis.conference.model.user.UserState;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    boolean existsByEmail(String email);

    List<User> findByUserState(UserState userState);

}
