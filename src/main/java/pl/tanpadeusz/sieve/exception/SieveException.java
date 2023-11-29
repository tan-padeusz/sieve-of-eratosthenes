package pl.tanpadeusz.sieve.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SieveException extends Exception {
    public SieveException(String message) {
        super(message);
    }
}