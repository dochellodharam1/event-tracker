package com.trickyjava.how.eventtracker.factory;

import com.trickyjava.how.eventtracker.model.EventSession;
import com.trickyjava.how.eventtracker.util.Utility;

public interface EventSessionFactory {
    static EventSession createEventSession(String userId, String sessionId) {
        EventSession eventSession = new EventSession();
        eventSession.setIp(Utility.getClientIPAddress());
        eventSession.setUserId(userId);
        eventSession.setSessionId(sessionId);
        return eventSession;
    }
}
