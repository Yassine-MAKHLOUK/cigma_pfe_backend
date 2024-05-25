package ma.org.licence.pfe.repositories;

import ma.org.licence.pfe.entities.Barber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarberRepository extends MongoRepository<Barber, String> {

    @Query("{_class: \"barber\"}")
    List<Barber> findAllBarbers();
    Optional<Barber> findByBarberName(String barberName);
    boolean existsByBarberName(String barberName);

}
