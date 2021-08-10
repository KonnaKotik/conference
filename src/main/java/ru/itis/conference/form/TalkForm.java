package ru.itis.conference.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TalkForm {
    private String name;

    private String description;

    private String link;

    private Date date;
}
