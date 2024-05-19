package ma.org.licence.pfe.entities;

import ma.org.licence.pfe.models.Dob;
import ma.org.licence.pfe.models.Location;
import ma.org.licence.pfe.models.Registered;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@TypeAlias("client")
public class Client extends User{
    private String gender;
    private Location location;
    private Dob dob;
    private Registered registered;
    private String phone;
    private String cell;
    private String nat;

}
