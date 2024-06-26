package ma.org.licence.pfe.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ma.org.licence.pfe.enums.Gender;
import ma.org.licence.pfe.enums.Role;
import ma.org.licence.pfe.models.*;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "users")
@TypeAlias("barber")
public class Barber extends User{

    private String barberName;
    private List<BarberPrestation> prestation;
    private List<Schedule> schedule;
    private Location address;

    public Barber() {
        super();
        this.setRole(Role.BARBER);
    }



    @Builder(builderMethodName = "barberBuilder")
    public Barber(String id, Name name, Gender gender, String email, String pwd, Login login, Picture picture, Role role, String barberName, List<BarberPrestation> prestation, List<Schedule> schedule) {
        super(id, name, email, pwd, login, picture, Role.BARBER);
        this.barberName = barberName;
        this.prestation = prestation;
        this.schedule = schedule;
    }
}
