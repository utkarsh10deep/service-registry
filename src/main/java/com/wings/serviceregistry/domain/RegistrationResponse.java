package com.wings.serviceregistry.domain;

import lombok.Data;
import org.json.JSONObject;

import java.io.Serializable;

@Data
public class RegistrationResponse {
    private String message;
    public RegistrationResponse(String message) {
        this.message = message;
    }
}
