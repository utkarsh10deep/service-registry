package com.wings.serviceregistry.controller;

import com.wings.serviceregistry.config.StartupConfig;
import com.wings.serviceregistry.service.DBReader;
import com.wings.serviceregistry.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.ParseException;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if(startupConfig.getServiceKey().equals(Base64Util.encode(authenticationKey).hashCode())) {
            if(!dbReader.isDirExists(serviceName)) {
                return ResponseEntity.badRequest().build();
            }
            if(!dbReader.isSecretExists(serviceName)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(dbReader.readSecrets(serviceName));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
