package br.edu.utfpr.pb.project.server.error;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiError {
    private long timestamp = new Date().getTime();
    private int status;
    private String url;
    private String message;
    private Map<String, String> validationErrors;

    public ApiError(final int status, final String message, final String url, final Map<String, String> validationErrors) {
        this.status = status;
        this.message = message;
        this.url = url;
        this.validationErrors = validationErrors;
    }

    public ApiError(final int status, final String message, final String url) {
        this.status = status;
        this.message = message;
        this.url = url;
    }
}
