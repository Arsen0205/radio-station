package com.example.radio_station.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    PERFORMER,
    LISTENER,
    ADMIN;

    @Override
    public String getAuthority(){
        return name();
    }
}
