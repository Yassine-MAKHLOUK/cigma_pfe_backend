package ma.org.licence.pfe.repositories;

import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.Book;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.models.BarberPrestation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Book save(Book book);
    List<Book> findAll();
    Optional<Book> findByBookingId(String id);
    List<Book> findByUser(User user);
    List<Book> findByBarber(Barber barber);
    Book findByBarberPrestationName(String prestationName);
    boolean existsByBookingId(String id);
    void deleteByBookingId(String id);

}
