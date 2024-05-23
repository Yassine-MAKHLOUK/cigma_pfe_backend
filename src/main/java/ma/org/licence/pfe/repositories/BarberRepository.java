package ma.org.licence.pfe.repositories;

import ma.org.licence.pfe.entities.Barber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarberRepository extends MongoRepository<Barber, String> {

    Optional<Barber> findByBarberName(String barberName);
    boolean existsByBarberName(String barberName);

}
