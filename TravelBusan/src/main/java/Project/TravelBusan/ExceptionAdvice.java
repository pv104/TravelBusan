package Project.TravelBusan;

import Project.TravelBusan.response.ResponseDto;
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
        return ResponseDto.fail( "실패!", e.getMessage().toString());
    }
}