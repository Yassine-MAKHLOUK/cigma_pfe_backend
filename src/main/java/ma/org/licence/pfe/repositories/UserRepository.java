package ma.org.licence.pfe.repositories;

import ma.org.licence.pfe.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    User save(User user);
    List<User> findAll();
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
    boolean existsById(String id);
    void deleteById(String id);



}
