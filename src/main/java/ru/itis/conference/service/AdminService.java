package ru.itis.conference.service;


import ru.itis.conference.form.UserCreateForm;

import java.util.List;

public interface AdminService {

    void createUser(UserCreateForm userCreateForm);
    void deleteUser(Long id);
    void updateUser(Long id, UserCreateForm userCreateForm);
    List<UserCreateForm> getListUser();



}
