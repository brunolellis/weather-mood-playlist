package io.github.brunolellis.playlist.adapter.web;

import lombok.Data;

import java.util.List;

@Data
public class ApiErrorResponse {
    private final List<ErrorMessage> errors;
}

@Data
class ErrorMessage {
    private final String message;
}
