package ma.org.licence.pfe.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarberPrestationRequest {
    private String token;
    private String name;
    private String description;
    private int price;
    private int promo;
}
