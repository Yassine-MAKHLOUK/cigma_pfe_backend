package ma.org.licence.pfe.requests;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.org.licence.pfe.enums.Role;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddRequest {
    private String firstname;
    private String middlename;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;
    private String password;
}
