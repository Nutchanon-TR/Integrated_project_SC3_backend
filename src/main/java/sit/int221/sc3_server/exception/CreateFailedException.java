package sit.int221.sc3_server.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CreateFailedException extends RuntimeException {
    public CreateFailedException (String message){
        super(message);
    }

}
