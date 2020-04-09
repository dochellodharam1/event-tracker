package com.trickyjava.how.eventtracker.repository;

import com.trickyjava.how.eventtracker.model.EventSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface EventSessionRepository extends MongoRepository<EventSession, String>, QueryByExampleExecutor<EventSession> {
    Optional<EventSession> findBySessionId(String sessionId);
}
