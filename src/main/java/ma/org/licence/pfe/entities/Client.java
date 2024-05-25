package ma.org.licence.pfe.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ma.org.licence.pfe.enums.Gender;
import ma.org.licence.pfe.enums.Role;
import ma.org.licence.pfe.models.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "users")
@TypeAlias("client")
public class Client extends User{
    private Gender gender;
    private Location location;
    private Dob dob;
    private Registered registered;
    private String phone;
    private String cell;
    private String nat;

    @Builder(builderMethodName = "clientBuilder")
    public Client(String id, Name name, String email, String pwd, Login login, Picture picture, Role role) {
        super(id, name, email, pwd, login, picture, Role.CLIENT);

    }

}
