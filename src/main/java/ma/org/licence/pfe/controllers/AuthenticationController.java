package ma.org.licence.pfe.controllers;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationServiceImp.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationServiceImp.authenticate(request));
    }
    @GetMapping("/hello")
    public ResponseEntity<Object> Hello(){
        return ResponseHandler.generateResponse("Hello", HttpStatus.OK, null);
    }

}
