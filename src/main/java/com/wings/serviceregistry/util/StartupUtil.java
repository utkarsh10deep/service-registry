package com.wings.serviceregistry.util;

import com.wings.serviceregistry.config.StartupConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StartupUtil {
    @Autowired
    private StartupConfig startupConfig;

    public void startupServiceAuthentication() {
        //TODO: Update StartupConfig.KEY on startup
    }

    public void printStartupConfigs() {
        log.info("Key: " + startupConfig.getKey());
        log.info("DB location: " + startupConfig.getDbLocation());
        log.info("Registration key: " + startupConfig.getServiceKey());
    }
}
