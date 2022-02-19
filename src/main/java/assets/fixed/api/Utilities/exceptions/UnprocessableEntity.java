package assets.fixed.api.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnprocessableEntity extends Exception{
    public UnprocessableEntity(String message){
        super(message);
    }
}
