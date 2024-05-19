package ma.org.licence.pfe.repositories;

import ma.org.licence.pfe.entities.Barber;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BarberRepository extends MongoRepository<Barber, String> {
}
