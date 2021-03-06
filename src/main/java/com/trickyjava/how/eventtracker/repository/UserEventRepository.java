package com.trickyjava.how.eventtracker.repository;

import com.trickyjava.how.eventtracker.model.EventSession;
import com.trickyjava.how.eventtracker.model.UserEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.time.Instant;

public interface UserEventRepository extends MongoRepository<UserEvent, String>, QueryByExampleExecutor<UserEvent> {
    Page<UserEvent> findAllByEventSession(EventSession eventSession, Pageable pageable);

    Page<UserEvent> findAllByEventSessionAndWhenCreatedLessThanEqual(EventSession eventSession, Instant whenCreated, Pageable pageable);

    Page<UserEvent> findAllByEventSessionAndWhenCreatedGreaterThanEqual(EventSession eventSession, Instant whenCreated, Pageable pageable);

    Page<UserEvent> findAllByEventSessionAndWhenCreatedBetween(EventSession eventSession, Instant whenCreatedAfter, Instant whenCreatedBefore, Pageable pageable);
}
