package ru.itis.conference.service;

import ru.itis.conference.form.HomeForm;
import ru.itis.conference.form.TalkCreateForm;
import ru.itis.conference.form.TalkForm;

import java.util.List;

public interface TalkService {

    void create(Long userId,TalkCreateForm talkCreateForm);
    void update(Long id, TalkCreateForm talkCreateForm);
    void delete(Long id);
    List<TalkForm> getAllTalksForSpeaker(Long userId);
    List<HomeForm> getAllTalks();
}
