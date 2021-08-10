package ru.itis.conference.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.conference.dto.TimeDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleForm {

    private String dayOfMonth;

    private String month;

    private String year;

    private String roomName;

    private TimeDto times;
}
