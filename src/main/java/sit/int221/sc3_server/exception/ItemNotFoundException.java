package sit.int221.sc3_server.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends RuntimeException {
    private String appErrorCode ="404-03";
    public ItemNotFoundException(String message){
        super(message);
    }

}
