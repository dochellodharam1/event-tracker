package com.trickyjava.how.eventtracker.repository;

import com.trickyjava.how.eventtracker.model.EventSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface EventSessionRepository extends MongoRepository<EventSession, String>, QueryByExampleExecutor<EventSession> {
}
