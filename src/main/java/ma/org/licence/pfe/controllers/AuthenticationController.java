package ma.org.licence.pfe.controllers;

import lombok.RequiredArgsConstructor;
import ma.org.licence.pfe.dto.user.UserDto;
import ma.org.licence.pfe.exceptions.BadRequestException;
import ma.org.licence.pfe.response.ResponseHandler;
import ma.org.licence.pfe.security.AuthenticationRequest;
import ma.org.licence.pfe.security.AuthenticationResponse;
import ma.org.licence.pfe.security.RegisterRequest;
import ma.org.licence.pfe.services.AuthenticationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImp authenticationServiceImp;


    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) throws BadRequestException {
        try {
            return ResponseHandler.generateResponse("User Added successfully!", HttpStatus.OK, authenticationServiceImp.register(request));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request){
        try {
            return ResponseHandler.generateResponse("Logged successfully!", HttpStatus.OK, authenticationServiceImp.authenticate(request));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
