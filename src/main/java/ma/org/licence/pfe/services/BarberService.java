package ma.org.licence.pfe.services;

import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.requests.BarberPrestationRequest;
import ma.org.licence.pfe.security.AuthenticationResponse;
import ma.org.licence.pfe.requests.BarberRegisterRequest;

import java.util.List;

public interface BarberService {
    public AuthenticationResponse barberRegister(BarberRegisterRequest request) throws Exception;
    public List<Barber> getAllBarbers() ;
    public Barber addBarberPrestation(BarberPrestationRequest request);
}
