package ma.org.licence.pfe.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarberPrestation {
    @Id
    private String prestationId;
    private String name;
    private String description;
    private int price;
    private int promo;

}
