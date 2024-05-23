package ma.org.licence.pfe.security;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.org.licence.pfe.enums.Gender;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarberRegisterRequest {
    private String barberName;
    private String email;
    private String password;
}
