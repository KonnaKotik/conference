package ru.itis.conference.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.conference.form.HomeForm;
import ru.itis.conference.form.TalkForm;
import ru.itis.conference.service.TalkService;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final TalkService talkService;

    public HomeController(TalkService talkService) {
        this.talkService = talkService;
    }

    @PostMapping
    @ApiOperation("Login user")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<HomeForm>> getAllTalks(){
        return ResponseEntity.ok().body(talkService.getAllTalks());
    }
}
