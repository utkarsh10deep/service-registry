package com.wings.serviceregistry.controller;

import com.wings.serviceregistry.config.StartupConfig;
import com.wings.serviceregistry.service.DBReader;
import com.wings.serviceregistry.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@Slf4j
public class ServiceController {
    @Autowired
    private StartupConfig startupConfig;
    @Autowired
    private DBReader dbReader;

    @GetMapping("/fetchSecrets")
    public ResponseEntity<String> fetchSecrets(@RequestHeader String serviceName, @RequestHeader String authenticationKey) throws JSONException, FileNotFoundException {
        if (startupConfig.getServiceKey().equals(Base64Util.encode(authenticationKey).hashCode())) {
            if (!dbReader.isDirExists(serviceName)) {
                return ResponseEntity.badRequest().build();
            }
            if (!dbReader.isSecretExists(serviceName)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(dbReader.readSecrets(serviceName));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
