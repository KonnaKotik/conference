package ru.itis.conference.service;

import lombok.SneakyThrows;
import org.hibernate.NonUniqueObjectException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.itis.conference.exception.BadRequestException;
import ru.itis.conference.form.HomeForm;
import ru.itis.conference.form.TalkCreateForm;
import ru.itis.conference.form.TalkForm;
import ru.itis.conference.mapper.ScheduleMapper;
import ru.itis.conference.mapper.TalkMapper;
import ru.itis.conference.model.Schedule;
import ru.itis.conference.model.Talk;
import ru.itis.conference.model.room.Room;
import ru.itis.conference.model.room.RoomName;
import ru.itis.conference.model.user.User;
import ru.itis.conference.repository.RoomRepository;
import ru.itis.conference.repository.ScheduleRepository;
import ru.itis.conference.repository.TalkRepository;
import ru.itis.conference.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class TalkServiceImpl implements TalkService {

    private final TalkRepository talkRepository;
    private final TalkMapper talkMapper;
    private final ScheduleMapper scheduleMapper;
    private final RoomRepository roomRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public TalkServiceImpl(TalkRepository talkRepository, TalkMapper talkMapper, ScheduleMapper scheduleMapper, RoomRepository roomRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.talkRepository = talkRepository;
        this.talkMapper = talkMapper;
        this.scheduleMapper = scheduleMapper;
        this.roomRepository = roomRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    private boolean isFree(List<Schedule> scheduleList, Schedule newSchedule) {
        if(scheduleList.isEmpty()){
            return true;
        }
        long newStart = newSchedule.getDateStart().getTime();
        long newEnd = newSchedule.getDateEnd().getTime();
        for (Schedule sch:scheduleList) {
            long start = sch.getDateStart().getTime();
            long end = sch.getDateEnd().getTime();
            if(newStart > start && newEnd < end) {
                return true;
            }
        }
        return false;
    }

    @SneakyThrows
    @Override
    public void create(Long userId, TalkCreateForm talkCreateForm) {
        if(talkRepository.existsByName(talkCreateForm.getName())) {
           throw new BadRequestException("Not a unique name");
        }
        Talk talk = talkMapper.convertTalkCreateFormToTalk(talkCreateForm);
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<User> userList = new LinkedList<>();
        userList.add(user);
        talk.setSpeakers(userList);


        Schedule schedule = scheduleMapper.convertFormToSchedule(talkCreateForm.getScheduleForm());
        Room room = roomRepository.findByName(RoomName.valueOf(talkCreateForm.getScheduleForm().getRoomName())).orElseThrow(EntityNotFoundException::new);

        List<Schedule> scheduleList = scheduleRepository.findAllByRoom(room);
        if(isFree(scheduleList, schedule)) {
            talk = talkRepository.save(talk);
            schedule.setRoom(room);
            schedule.setTalk(talk);
            scheduleRepository.save(schedule);
        } else throw new BadRequestException("Time for the talk is taken");

    }

    @Override
    public void update(Long id, TalkCreateForm talkCreateForm) {
        Talk talk = talkMapper.convertTalkCreateFormToTalk(talkCreateForm);
        talk.setId(id);
        talkRepository.save(talk);

    }

    @Override
    public void delete(Long id) {
        talkRepository.deleteById(id);

    }

    @Override
    public List<TalkForm> getAllTalksForSpeaker(Long userId) {
        List<Talk> talks = talkRepository.findAllBySpeakersId(userId);
        return talkMapper.convertTalkListToTalkFormList(talks);
    }

    @Override
    public List<HomeForm> getAllTalks() {
        List<Talk> talks = talkRepository.findAll();
        List<HomeForm> homeForms = talkMapper.convertTalksToHomeForm(talks);
        return homeForms;
    }
}
