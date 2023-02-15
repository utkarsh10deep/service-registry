package com.wings.serviceregistry.domain;

import lombok.Data;

@Data
public class RegistrationResponse {
    private String message;

    public RegistrationResponse(String message) {
        this.message = message;
    }
}
