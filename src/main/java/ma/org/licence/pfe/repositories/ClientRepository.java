package ma.org.licence.pfe.repositories;

import ma.org.licence.pfe.entities.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
}
