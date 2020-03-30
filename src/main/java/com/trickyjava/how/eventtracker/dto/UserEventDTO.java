package com.trickyjava.how.eventtracker.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserEventDTO {
    private String s;
    private String u;
    private Map<String, Object> metadata;

    public String getSessionId() {
        return s;
    }

    public String getUserId() {
        return u;
    }

    @JsonAnySetter
    public void setMetadata(String key, Object value) {
        if (null == metadata) {
            this.metadata = new HashMap<>();
        }
        this.metadata.put(key, value);
    }
}
