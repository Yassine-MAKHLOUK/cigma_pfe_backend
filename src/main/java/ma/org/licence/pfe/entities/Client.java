package ma.org.licence.pfe.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.org.licence.pfe.enums.Gender;
import ma.org.licence.pfe.enums.Role;
import ma.org.licence.pfe.models.*;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
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




    @PersistenceCreator
    public Client(String id, Name name, String email, String pwd, Login login, Picture picture, Role role, Gender gender, Location location, Dob dob, Registered registered, String phone, String cell, String nat) {
        super(id, name, email, pwd, login, picture, role);
        this.gender = gender;
        this.location = location;
        this.dob = dob;
        this.registered = registered;
        this.phone = phone;
        this.cell = cell;
        this.nat = nat;
    }

    @Builder(builderMethodName = "clientBuilder")
    public Client(String id, Name name, String email, String pwd, Login login, Picture picture, Role role) {
        super(id, name, email, pwd, login, picture, Role.CLIENT);

    }

}
