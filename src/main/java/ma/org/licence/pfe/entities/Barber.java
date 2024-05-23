package ma.org.licence.pfe.entities;

import lombok.Builder;
import ma.org.licence.pfe.enums.Gender;
import ma.org.licence.pfe.enums.Role;
import ma.org.licence.pfe.models.Login;
import ma.org.licence.pfe.models.Name;
import ma.org.licence.pfe.models.Picture;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@TypeAlias("barber")
public class Barber extends User{

    private String barberName;

    public Barber() {
        super();
        this.setRole(Role.BARBER);
    }

    @Builder(builderMethodName = "barberBuilder")
    public Barber(String id, Name name, Gender gender, String email, String pwd, Login login, Picture picture, Role role, String barberName) {
        super(id, name, gender, email, pwd, login, picture, Role.BARBER);
        this.barberName = barberName;
    }
}
