package ru.itis.conference.mapper;


import org.springframework.stereotype.Component;
import ru.itis.conference.form.ScheduleForm;
import ru.itis.conference.model.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleMapper {

    public Schedule convertFormToSchedule(ScheduleForm scheduleForm) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String dateStartString = scheduleForm.getDayOfMonth() + "." + scheduleForm.getMonth() + "." +
                scheduleForm.getYear() + " " + scheduleForm.getTimes().getHour() + ":" +
                scheduleForm.getTimes().getMinute() + ":00";
        Date dateStart = format.parse(dateStartString);

        int endTime = Integer.parseInt(scheduleForm.getTimes().getHour()) + 2;
        String dateEndString = scheduleForm.getDayOfMonth() + "." + scheduleForm.getMonth() + "." +
                scheduleForm.getYear() + " " + endTime + ":" +
                scheduleForm.getTimes().getMinute() + ":00";
        Date dateEnd = format.parse(dateEndString);

        return Schedule.builder()
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .build();

    }
}
