package Project.TravelBusan.exception;

import Project.TravelBusan.response.ResponseDto;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    /**
     * IllegalStateException 발생시 이 메소드실행
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<?> illegalArgumentExceptionAdvice(IllegalStateException e) {
        return ResponseDto.fail( "error", e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseDto<?> validationException(ValidationException e) {
        return ResponseDto.fail("403 Forbidden", e.getMessage());
    }

    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseDto<?> duplicateUserException(DuplicateUserException e) {
        return ResponseDto.fail("403 Forbidden", e.getMessage());
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<?> notFoundUserException(NotFoundUserException e) {
        return ResponseDto.fail("404 NotFound", e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<?> notFoundException(NotFoundException e) {
        return ResponseDto.fail("404 NotFound", e.getMessage());
    }

}
