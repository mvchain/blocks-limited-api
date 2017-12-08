package com.mvc.invite.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseGenerator<T> {

    @Autowired
    private ObjectMapper objectMapper;

    public String success(T data) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"status\":200,\"message\":\"success\",\"data\":");
        sb.append(objectMapper.writeValueAsString(data));
        sb.append("}");
        return sb.toString();
    }

    public String error(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"status\":500,\"message\":\"");
        sb.append(message);
        sb.append("\"}");
        return sb.toString();
    }

    public String fail(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"status\":400,\"message\":\"");
        sb.append(message);
        sb.append("\"}");
        return sb.toString();
    }

    public String notFound() {
        return "{\"status\":404,\"message\":\"Resources not found.\"}";
    }
}