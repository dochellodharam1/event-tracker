package com.trickyjava.how.eventtracker.dto;

import lombok.Data;

@Data
public class UserEventDTO {
    private String s;
    private String u;
    private String m;

    public String getSessionId() {
        return s;
    }

    public String getUserId() {
        return u;
    }

    public String getMetadata() {
        return m;
    }
}
