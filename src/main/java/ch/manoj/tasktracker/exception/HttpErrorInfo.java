package ch.manoj.tasktracker.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

public class HttpErrorInfo {

    @Getter
    private final ZonedDateTime timestamp;

    @Getter
    private final String path;

    private final HttpStatus httpStatus;
    @Getter

    private final String message;

    public HttpErrorInfo() {
        timestamp = null;
        this.httpStatus = null;
        this.path = null;
        this.message = null;
    }

    public HttpErrorInfo(HttpStatus httpStatus, String path, String message) {
        timestamp = ZonedDateTime.now();
        this.httpStatus = httpStatus;
        this.path = path;
        this.message = message;
    }

    public int getStatus() {
        return httpStatus.value();
    }

    public String getError() {
        return httpStatus.getReasonPhrase();
    }

}
