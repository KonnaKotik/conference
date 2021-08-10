package ru.itis.conference.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.conference.form.TalkCreateForm;
import ru.itis.conference.form.TalkForm;
import ru.itis.conference.service.TalkService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/talks")
public class TalkController {

    private final TalkService talkService;

    public TalkController(TalkService talkService) {
        this.talkService = talkService;
    }

    @ApiOperation("Get all taks for speaker")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SPEAKER')") // перепроверить доступ (или сделать на уровне фронта)
    public ResponseEntity<List<TalkForm>> getAllTalks(@RequestHeader("Authorization") String authorization,
                                                      @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(talkService.getAllTalksForSpeaker(id));
    }

    @ApiOperation("Create talk")
    @PostMapping(("/{id}"))
    @PreAuthorize("hasAuthority('SPEAKER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTalk(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id,
                           @Valid @RequestBody TalkCreateForm talkCreateForm) {
        talkService.create(id, talkCreateForm);
    }

    @ApiOperation("Delete talk")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SPEAKER')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTalk(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        talkService.delete(id);
    }

    @ApiOperation("Update talk")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SPEAKER')")
    @ResponseStatus(HttpStatus.OK)
    public void updateTalk(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id,
                           @Valid @RequestBody TalkCreateForm talkCreateForm) {
        talkService.update(id, talkCreateForm);
    }
}
