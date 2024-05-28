package ma.org.licence.pfe.services;

import lombok.RequiredArgsConstructor;
import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.enums.Role;
import ma.org.licence.pfe.models.BarberPrestation;
import ma.org.licence.pfe.models.Login;
import ma.org.licence.pfe.repositories.BarberRepository;
import ma.org.licence.pfe.requests.BarberPrestationRequest;
import ma.org.licence.pfe.security.AuthenticationResponse;
import ma.org.licence.pfe.requests.BarberRegisterRequest;
import ma.org.licence.pfe.security.JwtService;
import ma.org.licence.pfe.shared.UniqueIdGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BarberServiceImp implements BarberService{

    private final BarberRepository barberRepository;

    private final UserServiceImp userServiceImp;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    @Override
    public AuthenticationResponse barberRegister(BarberRegisterRequest request) throws Exception {

        String pwd = passwordEncoder.encode(request.getPassword());
        Login login =  new Login("", request.getEmail(), pwd);

        var user = Barber.barberBuilder()
                .barberName(request.getBarberName())
                .email(request.getEmail())
                .pwd(pwd)
                .login(login)
                .build();
        userServiceImp.addUser(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public List<Barber> getAllBarbers() {
        return barberRepository.findAllBarbers();
    }

    @Override
    public Barber addBarberPrestation(BarberPrestationRequest request) {
        String email = jwtService.extractUsername(request.getToken());
        Barber barber = barberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Barber not found"));

        // Ensure the user has the BARBER role
        if (barber.getRole() != Role.BARBER) {
            throw new RuntimeException("User does not have BARBER role");
        }

        String prestationId = UniqueIdGenerator.generateCustomId();
        BarberPrestation prestation = BarberPrestation.builder()
                .prestationId(prestationId)
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .promo(request.getPromo())
                .build();

        if (barber.getPrestation() == null) {
            barber.setPrestation(new ArrayList<>());
        }
        barber.getPrestation().add(prestation);

        barberRepository.save(barber);

        return barber;
    }

}
