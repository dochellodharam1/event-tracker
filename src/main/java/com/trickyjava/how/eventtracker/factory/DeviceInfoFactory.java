package com.trickyjava.how.eventtracker.factory;

import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;
import com.trickyjava.how.eventtracker.model.DeviceInfo;
import com.trickyjava.how.eventtracker.util.Utility;

public interface DeviceInfoFactory {

    UserAgentParser parser = getUserAgentParser();

    static UserAgentParser getUserAgentParser() {
        UserAgentParser parser = null;
        try {
            parser = new UserAgentService().loadParser();
        } catch (Exception e) {

        }
        return parser;
    }

    static DeviceInfo createDeviceInfo() {
        DeviceInfo source = new DeviceInfo();
        UserAgentParser userAgentParser = parser;
        if (null == userAgentParser) {
            userAgentParser = getUserAgentParser();
        }
        if (null != userAgentParser) {
            Capabilities capabilities = userAgentParser.parse(Utility.getUserAgent());

            String browser = capabilities.getBrowser();
            String browserType = capabilities.getBrowserType();
            String browserMajorVersion = capabilities.getBrowserMajorVersion();
            String deviceType = capabilities.getDeviceType();
            String platform = capabilities.getPlatform();
            String platformVersion = capabilities.getPlatformVersion();

            source.setBrowser(browser);
            source.setBrowserMajorVersion(browserMajorVersion);
            source.setBrowserType(browserType);
            source.setDeviceType(deviceType);
            source.setPlatform(platform);
            source.setPlatformVersion(platformVersion);
        }

        return source;
    }
}
