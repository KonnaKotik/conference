package ru.itis.conference.form;

import ru.itis.conference.dto.TimeDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomeForm {

    private String roomName;

    private String dayOfMonth;

    private String month;

    private String year;

    private TimeDto times;

    private String talkName;

    private String description;

}
