package com.trickyjava.how.eventtracker.repository;

import com.trickyjava.how.eventtracker.model.DeviceInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface DeviceInfoRepository extends MongoRepository<DeviceInfo, String>, QueryByExampleExecutor<DeviceInfo> {
}
