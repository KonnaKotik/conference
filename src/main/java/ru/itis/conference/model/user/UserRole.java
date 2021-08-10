package ru.itis.conference.model.user;

public enum UserRole {

    ADMIN("Администратор"),
    SPEAKER("Докладчик"),
    LISTENER("Слушатель");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
