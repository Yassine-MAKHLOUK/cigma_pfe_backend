package ma.org.licence.pfe.services;

import lombok.RequiredArgsConstructor;
import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.enums.BarberState;
import ma.org.licence.pfe.enums.Role;
import ma.org.licence.pfe.enums.WeekDays;
import ma.org.licence.pfe.models.*;
import ma.org.licence.pfe.repositories.BarberRepository;
import ma.org.licence.pfe.requests.BarberPrestationRequest;
import ma.org.licence.pfe.requests.SetAdressRequest;
import ma.org.licence.pfe.security.AuthenticationResponse;
import ma.org.licence.pfe.requests.BarberRegisterRequest;
import ma.org.licence.pfe.security.JwtService;
import ma.org.licence.pfe.shared.UniqueIdGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        // Check if the barber name exists
        if (barberRepository.existsByBarberName(request.getBarberName())) {
            throw new IllegalArgumentException("Barber already exist.");
        }

        String pwd = passwordEncoder.encode(request.getPassword());
        Login login = new Login(UniqueIdGenerator.generateCustomId(), request.getEmail(), pwd);

        List<BarberPrestation> prestation = new ArrayList<>();
        List<Schedule> schedule = createDefaultWeeklySchedule();

        Barber user = Barber.barberBuilder()
                .barberName(request.getBarberName())
                .email(request.getEmail())
                .pwd(pwd)
                .login(login)
                .prestation(prestation)
                .schedule(schedule)
                .build();

        userServiceImp.addUser(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private List<Schedule> createDefaultWeeklySchedule() {
        return Arrays.stream(WeekDays.values())
                .map(day -> new Schedule(day, LocalTime.of(0,0, 0), LocalTime.of(0,0, 0), BarberState.CLOSED, ""))
                .collect(Collectors.toList());
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

    @Override
    public Location setBarberAddress(String token, SetAdressRequest request) {
        String email = jwtService.extractUsername(request.getToken());
        Barber barber = barberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Barber not found"));
        Coordinates coordinates = new Coordinates(request.getLongitude(), request.getLatitude());
        var barberAddress = Location.builder()
                .address(request.getAddress())
                .state(request.getState())
                .city(request.getCity())
                .country(request.getCountry())
                .postcode(request.getPostcode())
                .coordinates(coordinates)
                .build();
        barber.setAddress(barberAddress);
        barberRepository.save(barber);
        return barber.getAddress();
    }

}
