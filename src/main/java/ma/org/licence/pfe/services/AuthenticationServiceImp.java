package ma.org.licence.pfe.services;

import lombok.RequiredArgsConstructor;
import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.enums.Gender;
import ma.org.licence.pfe.enums.Role;
import ma.org.licence.pfe.exceptions.BadRequestException;
import ma.org.licence.pfe.models.Login;
import ma.org.licence.pfe.models.Name;
import ma.org.licence.pfe.repositories.UserRepository;
import ma.org.licence.pfe.security.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService{

    private final UserRepository userRepository;
    private final UserServiceImp userServiceImp;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) throws BadRequestException {
        String title = request.getGender() == Gender.Male ? "Mr." : request.getGender() == Gender.Female ? "Ms." : "";

        Name name = new Name(title, request.getFirstname(), request.getMiddlename(), request.getLastname());
        String pwd = passwordEncoder.encode(request.getPassword());
        Login login =  new Login("", request.getEmail(), pwd);

        var user = User.builder()
                .name(name)
                .gender(request.getGender())
                .email(request.getEmail())
                .pwd(pwd)
                .login(login)
                .role(Role.CLIENT)
                .build();
        userServiceImp.addUser(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

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
    public List<User> getAllBarbers() {
        return userRepository.findByRole(Role.BARBER);
    }
}
