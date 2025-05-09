package sit.int221.sc3_server.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UpdateFailedException extends RuntimeException {
    private String appErrorCode = "500-01";

    public UpdateFailedException(String message) {
        super(message);
    }

}
