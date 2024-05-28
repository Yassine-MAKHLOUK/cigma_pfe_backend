package ma.org.licence.pfe.services;

import ma.org.licence.pfe.security.AuthenticationRequest;
import ma.org.licence.pfe.security.AuthenticationResponse;
import ma.org.licence.pfe.requests.RegisterRequest;

public interface AuthenticationService {

    public AuthenticationResponse authenticate(AuthenticationRequest request);
    public AuthenticationResponse register(RegisterRequest request) throws Exception;
    public String userRoleByEmail(String email);
}
