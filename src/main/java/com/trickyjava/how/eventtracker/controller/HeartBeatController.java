package com.trickyjava.how.eventtracker.controller;

import com.trickyjava.how.eventtracker.dto.UserEventDTO;
import com.trickyjava.how.eventtracker.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heart-beat")
public class HeartBeatController {

    @Autowired
    private UserEventService service;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<String> heartBeat(UserEventDTO dto) {
        service.heartBeat(dto);
        return ResponseEntity.ok("");
    }
}
