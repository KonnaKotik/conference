package ru.itis.conference.model.room;

import ru.itis.conference.model.user.UserRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum RoomName {
    AI("ИИ"),
    PRODUCT_DESIGN("Дизайн продукта"),
    SOFTWARE("ПО"),
    IT("Информационные технологии"),
    ROBOTIC("Робототехника"),
    DEVELOPMENT("Разработка");

    private final String name;

    RoomName(String name) {
        this.name = name;
    }
}
