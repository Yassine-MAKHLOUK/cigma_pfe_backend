package ma.org.licence.pfe.services;

import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.Book;
import ma.org.licence.pfe.entities.Client;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.models.BarberPrestation;
import ma.org.licence.pfe.repositories.BarberRepository;
import ma.org.licence.pfe.repositories.BookRepository;
import ma.org.licence.pfe.repositories.UserRepository;
import ma.org.licence.pfe.security.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class BookingServiceImp implements BookingService{

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BarberRepository barberRepository;
    @Autowired
    UserServiceImp userServiceImp;

    @Override
    public Book clientBook(BookRequest request) {
        User user = userRepository.findById(request.getUserId()).get();
        long prestationId = new Random().nextLong();
        var service = BarberPrestation.builder()
                .prestationId(prestationId+"")
                .prestationName(request.getService())
                .prestationPrice("50")
                .build();

        var book = Book.builder()
                .user(user)
                .barberPrestation(service)
                .createdAt(new Date())
                .build();
        bookRepository.save(book);
        return book;
    }
}
