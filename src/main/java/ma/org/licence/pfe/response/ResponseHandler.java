package ma.org.licence.pfe.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", responseObj);
        map.put("message", message);
        map.put("status", status.value());

        return new ResponseEntity<Object>(map, status);
    }
        public static ResponseEntity<Object> generateErrorResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());

        return new ResponseEntity<Object>(map, status);
    }

}
