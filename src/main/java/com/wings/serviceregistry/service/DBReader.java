package com.wings.serviceregistry.service;

import com.wings.serviceregistry.config.StartupConfig;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

@Service
public class DBReader {
    @Autowired
    StartupConfig config;
    public String readSecrets(String serviceName) throws FileNotFoundException, JSONException {
        File file = new File(config.getDbLocation()
                + config.getDirectorySeparator()
                + serviceName
                + config.getDirectorySeparator()
                + "secrets.txt" );
        return new Scanner(file).useDelimiter("\\Z").next();
    }

    public boolean isDirExists(String serviceName) {
        File file = new File(config.getDbLocation()
                + config.getDirectorySeparator()
                + serviceName);
        return file.exists();
    }

    public boolean isSecretExists(String serviceName) {
        File file = new File(config.getDbLocation()
                + config.getDirectorySeparator()
                + serviceName
                + config.getDirectorySeparator()
                + "secrets.txt" );
        return file.exists();
    }

}
