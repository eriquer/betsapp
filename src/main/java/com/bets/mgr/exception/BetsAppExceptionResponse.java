package com.bets.mgr.exception;

import java.time.LocalDateTime;

public class BetsAppExceptionResponse {

    private LocalDateTime datwe;
    private String message;
    private String details;

    public BetsAppExceptionResponse(LocalDateTime datwe, String message, String details) {
        this.datwe = datwe;
        this.message = message;
        this.details = details;
    }

    public BetsAppExceptionResponse(LocalDateTime datwe) {
        this.datwe = datwe;
    }

    public LocalDateTime getDatwe() {
        return datwe;
    }

    public void setDatwe(LocalDateTime datwe) {
        this.datwe = datwe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
