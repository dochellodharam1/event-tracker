package com.trickyjava.how.eventtracker.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.Map;

@Data
public class UserEventDTO {
    private String s;
    private String u;

    @JsonAnySetter
    private Map<String, Object> metadata;

    public String getSessionId() {
        return s;
    }

    public String getUserId() {
        return u;
    }
}
