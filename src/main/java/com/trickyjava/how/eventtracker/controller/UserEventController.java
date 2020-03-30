package com.trickyjava.how.eventtracker.controller;

import com.trickyjava.how.eventtracker.dto.UserEventDTO;
import com.trickyjava.how.eventtracker.model.UserEvent;
import com.trickyjava.how.eventtracker.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class UserEventController {

    @Autowired
    private UserEventService service;

    @GetMapping("/search")
    public ResponseEntity<Page<UserEvent>> search(UserEvent userEvent,
                                                  @PageableDefault(page = 0, size = 20)
                                                  @SortDefault.SortDefaults({
                                                          @SortDefault(sort = "id", direction = Sort.Direction.ASC)
                                                  }) Pageable pageable) {
        return ResponseEntity.ok(service.search(userEvent, pageable));
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<String> create(UserEventDTO dto) {
        service.create(dto);
        return ResponseEntity.ok("");
    }
}
