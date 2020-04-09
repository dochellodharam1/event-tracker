package com.trickyjava.how.eventtracker.controller;

import com.trickyjava.how.eventtracker.model.EventSession;
import com.trickyjava.how.eventtracker.model.UserEvent;
import com.trickyjava.how.eventtracker.repository.EventSessionRepository;
import com.trickyjava.how.eventtracker.repository.UserEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private EventSessionRepository repository;

    @Autowired
    private UserEventRepository eventRepository;

    @Autowired
    private EventSessionRepository sessionRepository;

    @CrossOrigin
    @GetMapping("/sessions")
    public ResponseEntity<Page<EventSession>> sessions(@PageableDefault(page = 0, size = 20)
                                                       @SortDefault.SortDefaults({
                                                               @SortDefault(sort = "lastHeartBeat", direction = Sort.Direction.DESC)
                                                       }) Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    @CrossOrigin
    @GetMapping("/sessions/{id}/events")
    public ResponseEntity<Page<UserEvent>> events(@PathVariable("id") String sessionId,
                                                  @RequestParam(name = "before", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant before,
                                                  @RequestParam(name = "after", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant after,
                                                  @PageableDefault(page = 0, size = 20)
                                                  @SortDefault.SortDefaults({
                                                          @SortDefault(sort = "whenCreated", direction = Sort.Direction.DESC)
                                                  }) Pageable pageable) {
        return ResponseEntity.ok(
                sessionRepository.findBySessionId(sessionId)
                        .map(eventSession ->
                                Optional.ofNullable(after)
                                        .map(instantAfter -> Optional.ofNullable(before)
                                                .map(instantBefore -> eventRepository.findAllByEventSessionAndWhenCreatedBetween(eventSession, instantAfter, instantBefore, pageable))
                                                .orElseGet(() -> eventRepository.findAllByEventSessionAndWhenCreatedGreaterThanEqual(eventSession, instantAfter, pageable))
                                        )
                                        .orElseGet(() -> Optional.ofNullable(before)
                                                .map(instantBefore -> eventRepository.findAllByEventSessionAndWhenCreatedLessThanEqual(eventSession, instantBefore, pageable))
                                                .orElseGet(() -> eventRepository.findAllByEventSession(eventSession, pageable))))
                        .orElse(null));
    }

}
