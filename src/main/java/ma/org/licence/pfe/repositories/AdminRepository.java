package ma.org.licence.pfe.repositories;

import ma.org.licence.pfe.entities.Admin;
import ma.org.licence.pfe.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin save(User user);
    List<Admin> findAll();
    Optional<Admin> findById(String id);
    Optional<Admin> findByEmail(String email);
    boolean existsById(String id);
    void deleteById(String id);

}
