package ma.org.licence.pfe.services;

import lombok.RequiredArgsConstructor;
import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.Book;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.enums.BookingStatus;
import ma.org.licence.pfe.models.BarberPrestation;
import ma.org.licence.pfe.repositories.BarberRepository;
import ma.org.licence.pfe.repositories.BookRepository;
import ma.org.licence.pfe.repositories.UserRepository;
import ma.org.licence.pfe.requests.BookRequest;
import ma.org.licence.pfe.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BookingServiceImp implements BookingService{

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BarberRepository barberRepository;
    @Autowired
    UserServiceImp userServiceImp;

    private final JwtService jwtService;

    @Override
    public Book clientBook(BookRequest request) {
        String email = jwtService.extractUsername(request.getToken());
        User user = userRepository.findByEmail(email).get();
        Barber barber = barberRepository.findByBarberName(request.getBarberName()).get();
        long prestationId = new Random().nextLong();
        var service = BarberPrestation.builder()
                .prestationId(prestationId+"")
                .name(request.getService())
                .price(50)
                .build();

        var book = Book.builder()
                .barber(barber)
                .user(user)
                .bookingTime(LocalDate.parse(request.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .barberPrestation(service)
                .createdAt(new Date())
                .bookingStatus(BookingStatus.OnHold)
                .build();
        bookRepository.save(book);
        return book;
    }

    @Override
    public List<Book> getBookByUser(String token) {
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email).get();
        return bookRepository.findByUser(user);
    }

    @Override
    public List<Book> getBookByBarber(String token) {
        String email = jwtService.extractUsername(token);
        Barber barber = barberRepository.findByEmail(email).get();
        return bookRepository.findByBarber(barber);
    }
}
