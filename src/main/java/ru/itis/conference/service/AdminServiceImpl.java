package ru.itis.conference.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.conference.form.UserCreateForm;
import ru.itis.conference.mapper.UserMapper;
import ru.itis.conference.model.user.User;
import ru.itis.conference.model.user.UserState;
import ru.itis.conference.repository.UserRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AdminServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public void createUser(UserCreateForm userCreateForm) {
        if(!userRepository.existsByEmail(userCreateForm.getEmail())) {
            userCreateForm.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
            User user = userMapper.convertUserCreateFormToUser(userCreateForm);
            user.setUserState(UserState.ACTIVE);
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Email is already used");
        }

    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setUserState(UserState.DELETED);

    }

    @Override
    public void updateUser(Long id, UserCreateForm userCreateForm) {
        User updateUser = userMapper.convertUserCreateFormToUser(userCreateForm);
        updateUser.setId(id);
        updateUser.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
        userRepository.save(updateUser);



    }

    @Override
    public List<UserCreateForm> getListUser() {
        List<User> users = userRepository.findByUserState(UserState.ACTIVE);
        return userMapper.convertUserToUserCreateFormList(users);
    }
}
