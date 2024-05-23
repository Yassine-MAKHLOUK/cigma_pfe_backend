package ma.org.licence.pfe.repositories;

import ma.org.licence.pfe.entities.Book;
import ma.org.licence.pfe.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Book save(Book book);
    List<Book> findAll();
    Optional<Book> findByBookingId(String id);
    boolean existsByBookingId(String id);
    void deleteByBookingId(String id);

}
