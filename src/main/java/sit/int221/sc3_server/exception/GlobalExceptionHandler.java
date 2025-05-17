package sit.int221.sc3_server.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException exception , HttpServletRequest request){
        GeneralErrorResponse ger = new GeneralErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ger);
    }

    @ExceptionHandler(CreateFailedException.class)
    public ResponseEntity<Object> handleInternalException(Exception e,HttpServletRequest httpServletRequest){
        GeneralErrorResponse ger = new GeneralErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Sale item create failed",
                e.getMessage(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ger);
    }

    @ExceptionHandler(UpdateFailedException.class)
    public ResponseEntity<Object> handleUpdateFailedException(Exception e,HttpServletRequest httpServletRequest){
        GeneralErrorResponse ger = new GeneralErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Sale item updated failed",
                e.getMessage(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ger);
    }

    @ExceptionHandler(DeleteFailedException.class)
    public ResponseEntity<Object> handleDeleteFailedException(Exception e,HttpServletRequest httpServletRequest){
        GeneralErrorResponse ger = new GeneralErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "brand delete failed",
                e.getMessage(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ger);
    }

}
