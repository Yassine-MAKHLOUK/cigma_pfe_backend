package ma.org.licence.pfe.services;

import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.enums.Role;
import ma.org.licence.pfe.security.AuthenticationRequest;
import ma.org.licence.pfe.security.AuthenticationResponse;
import ma.org.licence.pfe.security.BarberRegisterRequest;
import ma.org.licence.pfe.security.RegisterRequest;

import java.util.List;

public interface AuthenticationService {

    public AuthenticationResponse authenticate(AuthenticationRequest request);
    public AuthenticationResponse register(RegisterRequest request) throws Exception;
    public String userRoleByEmail(String email);
}
