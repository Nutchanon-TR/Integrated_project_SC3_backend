package sit.int221.sc3_server.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DeleteFailedException extends RuntimeException {
    public DeleteFailedException (String message){
        super(message);
    }

}
