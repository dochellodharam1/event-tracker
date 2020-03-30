package com.trickyjava.how.eventtracker.repository;

import com.trickyjava.how.eventtracker.model.UserEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserEventRepository extends MongoRepository<UserEvent, String>, QueryByExampleExecutor<UserEvent> {
}
