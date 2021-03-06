package com.trickyjava.how.eventtracker.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@ToString
@Document(collection = "eventSession")
public class EventSession {
    @Id
    private String id;
    private String sessionId;
    private String userId;
    private String ip;
    private Instant whenCreated;
    private Instant lastHeartBeat;
}
