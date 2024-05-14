package ma.org.licence.pfe.dto.user;

import ma.org.licence.pfe.entities.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getPicture(),
                user.getRole()
        );
    }


}
