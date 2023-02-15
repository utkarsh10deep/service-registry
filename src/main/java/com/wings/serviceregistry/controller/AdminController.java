package com.wings.serviceregistry.controller;

import com.wings.serviceregistry.config.StartupConfig;
import com.wings.serviceregistry.domain.RegistrationResponse;
import com.wings.serviceregistry.service.DBReader;
import com.wings.serviceregistry.util.Base64Util;
import com.wings.serviceregistry.util.RegistrationUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@Slf4j
public class AdminController {

    @Autowired
    StartupConfig startupConfig;
    @Autowired
    RegistrationUtil registrationUtil;
    @Autowired
    DBReader dbReader;

    @RequestMapping(value = "register-service", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RegistrationResponse> registerService(@RequestHeader String serviceName, @RequestHeader String authenticationKey, @RequestBody String request) throws FileNotFoundException, JSONException {
        JSONObject secrets = new JSONObject(request);
        if(startupConfig.getServiceKey().equals(Base64Util.encode(authenticationKey).hashCode())) {
            if(! registrationUtil.createServiceDirectory(serviceName)) {
                log.info("The service "+serviceName+" already exists in the DB.");
            }
            log.info("The service "+serviceName+" is registered.");
            log.info("Moving ahed to write/replace secrets");
            try {
                registrationUtil.createSecretFile(secrets,serviceName);
            } catch (IOException e) {
                return ResponseEntity.badRequest().build();
            }
            log.info("Service Registration is done");
            return ResponseEntity.ok(
                    new RegistrationResponse(
                            "Service registration is done successfully for given secrets"));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

}
