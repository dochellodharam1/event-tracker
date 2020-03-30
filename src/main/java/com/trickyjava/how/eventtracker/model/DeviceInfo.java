package com.trickyjava.how.eventtracker.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "deviceInfo")
public class DeviceInfo {
    @Id
    private String id;
    private String browser;
    private String browserType;
    private String browserMajorVersion;
    private String deviceType;
    private String platform;
    private String platformVersion;
}
