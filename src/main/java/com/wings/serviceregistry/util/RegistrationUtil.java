package com.wings.serviceregistry.util;

import com.wings.serviceregistry.config.StartupConfig;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class RegistrationUtil {

    @Autowired
    StartupConfig config;

    public boolean createServiceDirectory(String serviceName) {
        File directory = new File(config.getDbLocation() + config.getDirectorySeparator() + serviceName );
        if (! directory.exists()){
            directory.mkdir();
            return true;
        }
        else {
            return false;
        }
    }
    public void createSecretFile(JSONObject jsonObject, String serviceName) throws IOException {
        FileWriter file = new FileWriter(config.getDbLocation()
                + config.getDirectorySeparator()
                + serviceName
                + config.getDirectorySeparator()
                + "secrets.txt" );
        file.write(jsonObject.toString());
        file.close();
    }
}
