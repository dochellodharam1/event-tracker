package com.trickyjava.how.eventtracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trickyjava.how.eventtracker.dto.UserEventDTO;
import com.trickyjava.how.eventtracker.factory.DeviceInfoFactory;
import com.trickyjava.how.eventtracker.factory.EventSessionFactory;
import com.trickyjava.how.eventtracker.model.DeviceInfo;
import com.trickyjava.how.eventtracker.model.EventSession;
import com.trickyjava.how.eventtracker.model.UserEvent;
import com.trickyjava.how.eventtracker.repository.DeviceInfoRepository;
import com.trickyjava.how.eventtracker.repository.EventSessionRepository;
import com.trickyjava.how.eventtracker.repository.UserEventRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@Log4j2
public class UserEventService {

    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Autowired
    private EventSessionRepository eventSessionRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private AsyncExecutorService asyncExecutorService;

    public Page<UserEvent> search(UserEvent userEvent, Pageable pageable) {
        return userEventRepository.findAll(Example.of(userEvent), pageable);
    }

    public void create(UserEventDTO dto) {
        EventSession eventSession = EventSessionFactory.createEventSession(dto.getUserId(), dto.getSessionId());
        DeviceInfo deviceInfo = DeviceInfoFactory.createDeviceInfo();
        UserEvent userEvent = new UserEvent();
        asyncExecutorService.execute(() -> {
            userEvent.setMetadata(dto.getMetadata());
            userEvent.setWhenCreated(Instant.now());
            EventSession persistedEventSession = eventSessionRepository.findOne(Example.of(eventSession))
                    .orElseGet(() -> eventSessionRepository.save(eventSession));
            userEvent.setEventSession(persistedEventSession);
            DeviceInfo persistedDeviceInfo = deviceInfoRepository.findOne(Example.of(deviceInfo))
                    .orElseGet(() -> deviceInfoRepository.save(deviceInfo));
            userEvent.setDeviceInfo(persistedDeviceInfo);

            userEventRepository.save(userEvent);
            return true;
        });
    }

    public void heartBeat(UserEventDTO dto) {
        EventSession eventSession = EventSessionFactory.createEventSession(dto.getUserId(), dto.getSessionId());
        EventSession persistedEventSession = eventSessionRepository.findOne(Example.of(eventSession))
                .orElseGet(() -> eventSessionRepository.save(eventSession));
        persistedEventSession.setLastHeartBeat(Instant.now());
        eventSessionRepository.save(persistedEventSession);
    }
}
