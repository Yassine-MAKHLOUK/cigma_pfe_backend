package ma.org.licence.pfe.dto.user;

import ma.org.licence.pfe.enums.Role;
import ma.org.licence.pfe.models.Login;
import ma.org.licence.pfe.models.Name;
import ma.org.licence.pfe.models.Picture;

public record UserDto(
         String id,
         Name name,
         String email,
         Login login,
         Picture picture,
         Role role
) {
}
