package ma.org.licence.pfe.services;

import lombok.RequiredArgsConstructor;
import ma.org.licence.pfe.dto.user.UserDto;
import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.Book;
import ma.org.licence.pfe.entities.Client;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.enums.BookingStatus;
import ma.org.licence.pfe.enums.Gender;
import ma.org.licence.pfe.exceptions.BadRequestException;
import ma.org.licence.pfe.exceptions.ResourceNotFoundException;
import ma.org.licence.pfe.exceptions.UserNotFoundException;
import ma.org.licence.pfe.models.BarberPrestation;
import ma.org.licence.pfe.models.Login;
import ma.org.licence.pfe.models.Name;
import ma.org.licence.pfe.models.Schedule;
import ma.org.licence.pfe.repositories.BarberRepository;
import ma.org.licence.pfe.repositories.BookRepository;
import ma.org.licence.pfe.repositories.ClientRepository;
import ma.org.licence.pfe.repositories.UserRepository;
import ma.org.licence.pfe.requests.BarberRegisterRequest;
import ma.org.licence.pfe.requests.BookRequest;
import ma.org.licence.pfe.requests.UserAddRequest;
import ma.org.licence.pfe.security.JwtService;
import ma.org.licence.pfe.shared.UniqueIdGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {

    private final UserRepository userRepository;
    private final BarberRepository barberRepository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;

    private final UserServiceImp userServiceImp;
    private final BarberServiceImp barberServiceImp;
    private final BookingServiceImp bookingServiceImp;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    /***
     * User Methods
     * ***/

    @Override
    public List<User> getAllUsers() {
        return  userRepository.findAll();
    }

    @Override
    public User addUser(UserAddRequest userRequest) {

        Name UserName = new Name("", userRequest.getFirstname(), userRequest.getMiddlename(), userRequest.getLastname());
        String pwd = passwordEncoder.encode(userRequest.getPassword());
        Optional<User> existingUser = userRepository.findByEmail(userRequest.getEmail());

        if (existingUser.isPresent()) {
            throw new BadRequestException("User already exists");
        }

        // Create a new User object from the request
        var newUser = User.builder()
                .name(UserName)
                .email(userRequest.getEmail())
                .pwd(pwd)
                .role(userRequest.getRole())
                .build();

        // Save the new user
        return userRepository.save(newUser);
    }

    @Override
    public User getUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            System.out.println(userOptional.get().toString());
            return userOptional.get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("User with email [%s] not found", email)
            );
        }
    }

    @Override
    public User updateUser(String id, User newUser) {
        // Find the old user by id
        Optional<User> optionalOldUser = userRepository.findById(id);
        String pwd = passwordEncoder.encode(newUser.getPwd());
        // Check if the user exists
        if (!optionalOldUser.isPresent()) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }

        User oldUser = optionalOldUser.get();

        // Update the old user's details with new user's details
        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPwd(pwd);
        oldUser.setLogin(newUser.getLogin());
        oldUser.setPicture(newUser.getPicture());
        oldUser.setRole(newUser.getRole());


        User updatedUser = userRepository.save(oldUser);

        return updatedUser;
    }

    @Override
    public void deleteUser(String id) {
        userServiceImp.deleteUser(id);
    }


    /***
     * Barber Methods
     * ***/
    @Override
    public Barber addBarber(BarberRegisterRequest request) {
        // Check if the barber name exists
        if (barberRepository.existsByBarberName(request.getBarberName())) {
            throw new IllegalArgumentException("Barber already exist.");
        }

        String pwd = passwordEncoder.encode(request.getPassword());
        Login login = new Login(UniqueIdGenerator.generateCustomId(), request.getEmail(), pwd);

        List<BarberPrestation> prestation = new ArrayList<>();
        List<Schedule> schedule = barberServiceImp.createDefaultWeeklySchedule();

        Barber barber = Barber.barberBuilder()
                .barberName(request.getBarberName())
                .email(request.getEmail())
                .pwd(pwd)
                .login(login)
                .prestation(prestation)
                .schedule(schedule)
                .build();

        userServiceImp.addUser(barber);
        return barber;

    }

    @Override
    public Barber getBarber(String barberName) {
        return barberServiceImp.getBarber(barberName);
    }

    @Override
    public Barber updateBarber(String barberName, Barber newBarber) {
        // Find the old user by id
        Optional<Barber> optionalOldBarber = barberRepository.findByBarberName(barberName);

        // Check if the user exists
        if (!optionalOldBarber.isPresent()) {
            throw new UserNotFoundException( barberName + " not found");
        }

        Barber oldBarber = optionalOldBarber.get();

        // Update the old user's details with new user's details
        oldBarber.setName(newBarber.getName());
        oldBarber.setEmail(newBarber.getEmail());
        oldBarber.setPwd(newBarber.getPwd());
        oldBarber.setLogin(newBarber.getLogin());
        oldBarber.setPicture(newBarber.getPicture());
        oldBarber.setBarberName(newBarber.getBarberName());
        oldBarber.setPrestation(newBarber.getPrestation());
        oldBarber.setSchedule(newBarber.getSchedule());

        // Update other fields as necessary

        // Save the updated user back to the repository
        Barber updatedBarber = barberRepository.save(oldBarber);

        // Return the updated user
        return updatedBarber;
    }




    /***
     * Client Methods
     * ***/
    @Override
    public Client addClient(Client client) {
        return null;
    }

    @Override
    public Client getClient(String email) {
        return null;
    }

    @Override
    public Client updateClient(String email) {
        return null;
    }




    /***
     * Bookings Methods
     * ***/

    @Override
    public Book addBook(BookRequest book, String email) {
        User user = userRepository.findByEmail(email).get();
        Barber barber = barberRepository.findByBarberName(book.getBarberName()).get();
        String bookingId = UniqueIdGenerator.generateCustomId();

        List<String> barberServices = barber.getPrestation()
                .stream()
                .map(BarberPrestation::getName)
                .toList();

        if (!barberServices.contains(book.getService())){
            throw new ResourceNotFoundException(book.getService() +" Not found");
        }

        var booking = Book.builder()
                .bookingId(bookingId)
                .barber(barber)
                .user(user)
                .prestationName(book.getService())
                .createdAt(new Date())
                .bookingStatus(BookingStatus.OnHold)
                .build();
        bookRepository.save(booking);
        return booking;
    }

    @Override
    public Book getBook(String email) {
        return null;
    }

    @Override
    public Book updateBook(String id, Book newBooking) {
        Book booking = bookRepository.findByBookingId(id).get();
        booking.setBarber(newBooking.getBarber());
        booking.setUser(newBooking.getUser());
        booking.setBookingTime(newBooking.getBookingTime());
        booking.setBookingStatus(newBooking.getBookingStatus());
        List<String> barberServices = newBooking.getBarber().getPrestation()
                .stream()
                .map(BarberPrestation::getName)
                .toList();

        if (!barberServices.contains(newBooking.getPrestationName())){
            throw new ResourceNotFoundException(newBooking.getPrestationName() +" Not found");
        }
        booking.setPrestationName(newBooking.getPrestationName());
        booking.setUpdatedAt(new Date());

        bookRepository.save(booking);
        return booking;
    }

    @Override
    public void deleteBook(String bookId) {
        bookRepository.deleteByBookingId(bookId);
    }
}
