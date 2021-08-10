package ru.itis.conference.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.conference.model.Schedule;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TalkCreateForm {

    private String name;

    private String description;

    private String link;

    private ScheduleForm scheduleForm;

}
