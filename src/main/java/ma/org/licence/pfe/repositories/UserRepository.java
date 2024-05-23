package ma.org.licence.pfe.repositories;

import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    User save(User user);
    List<User> findAll();
    Optional<User> findByEmail(String email);
    Optional<Barber> findBybarberName(String barbername);
    List<User> findByRole(Role role);
    boolean existsById(String id);
    void deleteById(String id);



}
