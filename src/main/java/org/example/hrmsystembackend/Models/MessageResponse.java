package org.example.hrmsystembackend.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MessageResponse {
    private long status_code;
    private String message_status;
    private String message;
    private Object data;

    public MessageResponse() {
    }

    public MessageResponse(long status_code, String message, Object data) {
        this.status_code = status_code;
        this.message = message;
        this.data = data;
    }

    public MessageResponse(long status_code, String message) {
        this.status_code = status_code;
        this.message = message;
    }

    public MessageResponse(long status_code, String message_status, String message) {
        this.status_code = status_code;
        this.message_status = message_status;
        this.message = message;
    }
    public MessageResponse(long status_code, String message_status, String message, Object data) {
        this.status_code = status_code;
        this.message_status = message_status;
        this.message = message;
        this.data = data;
    }

}