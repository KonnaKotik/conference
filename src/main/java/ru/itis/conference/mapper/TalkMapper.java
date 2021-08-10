package ru.itis.conference.mapper;

import org.springframework.stereotype.Component;
import ru.itis.conference.dto.TimeDto;
import ru.itis.conference.form.HomeForm;
import ru.itis.conference.form.TalkCreateForm;
import ru.itis.conference.form.TalkForm;
import ru.itis.conference.form.UserCreateForm;
import ru.itis.conference.model.Talk;
import ru.itis.conference.model.user.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TalkMapper {

    public Talk convertTalkCreateFormToTalk(TalkCreateForm talkCreateForm) {
        return Talk.builder()
                .name(talkCreateForm.getName())
                .description(talkCreateForm.getDescription())
                .link(talkCreateForm.getDescription())
                .build();
    }

    public TalkForm convertTalkTOTalkForm(Talk talk) {
        return TalkForm.builder()
                .name(talk.getName())
                .link(talk.getLink())
                .description(talk.getDescription())
                .date(talk.getSchedule().getDateStart())
                .build();
    }



    public HomeForm convertTalkToHomeForm(Talk talk){
        SimpleDateFormat dateFormat =null;
        Date date = talk.getSchedule().getDateStart();
        dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("MM");
        String month = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("dd");
        String day = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("HH");
        String hour = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("mm");
        String minute = dateFormat.format(date);
        TimeDto timeDto = new TimeDto();
        timeDto.setHour(hour);
        timeDto.setMinute(minute);
        return HomeForm.builder()
                .roomName(talk.getSchedule().getRoom().getName().toString())
                .description(talk.getDescription())
                .talkName(talk.getName())
                .year(year)
                .dayOfMonth(day)
                .month(month)
                .times(timeDto)
                .build();
    }

    private Stream<TalkForm> modelsToForm(List<Talk> talks) {

        return talks.stream().map(this::convertTalkTOTalkForm);
    }
    private Stream<HomeForm> modelsToHomeForm(List<Talk> talks) {
        return talks.stream().map(this::convertTalkToHomeForm);
    }

    public List<TalkForm> convertTalkListToTalkFormList(List<Talk> talks) {
        return modelsToForm(talks).collect(Collectors.toList());
    }

    public List<HomeForm> convertTalksToHomeForm(List<Talk> talks) {
        return modelsToHomeForm(talks).collect(Collectors.toList());
    }
}
