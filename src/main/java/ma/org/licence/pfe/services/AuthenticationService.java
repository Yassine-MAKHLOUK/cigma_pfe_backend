package ma.org.licence.pfe.services;

import ma.org.licence.pfe.security.AuthenticationRequest;
import ma.org.licence.pfe.security.AuthenticationResponse;
import ma.org.licence.pfe.security.BarberRegisterRequest;
import ma.org.licence.pfe.security.RegisterRequest;

public interface AuthenticationService {

    public AuthenticationResponse authenticate(AuthenticationRequest request);
    public AuthenticationResponse register(RegisterRequest request) throws Exception;
    public AuthenticationResponse barberRegister(BarberRegisterRequest request) throws Exception;
}
