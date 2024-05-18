package ma.org.licence.pfe.services;

import ma.org.licence.pfe.security.AuthenticationRequest;
import ma.org.licence.pfe.security.AuthenticationResponse;
import ma.org.licence.pfe.security.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse register(RegisterRequest request) throws Exception;
}
