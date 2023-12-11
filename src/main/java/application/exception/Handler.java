package application.exception;

import api.dto.exception.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class Handler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ExceptionResponse exceptionResponse(Exception exception) {
        return new ExceptionResponse(HttpStatus.FAILED_DEPENDENCY.value(),
                exception.getMessage());
    }
}
