package ma.org.licence.pfe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

public class UserNotFoundException extends ErrorResponseException {
    public UserNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND);
    }
}
