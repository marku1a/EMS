package com.example.ems.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    @JsonValue
    Completed("Completed"),
    @JsonValue
    In_progress("In_progress"),
    @JsonValue
    Cancelled("Cancelled"),
    @JsonValue
    Not_assigned("Not_assigned");
    
    private String value;
    
    Status(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}