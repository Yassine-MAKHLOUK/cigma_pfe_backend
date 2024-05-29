package ma.org.licence.pfe.requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetAdressRequest {
    private String token;
    private String address;
    private int postcode;
    private String city;
    private String state;
    private String country;
    private String longitude;
    private String latitude;
}
