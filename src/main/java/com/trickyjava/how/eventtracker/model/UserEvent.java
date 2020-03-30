package com.trickyjava.how.eventtracker.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@ToString
@Document(collection = "userEvent")
public class UserEvent {
    @Id
    private String id;
    private String metadata;
    private Instant whenCreated;

    @DBRef(lazy = false)
    private DeviceInfo deviceInfo;

    @DBRef(lazy = false)
    private EventSession eventSession;
}
