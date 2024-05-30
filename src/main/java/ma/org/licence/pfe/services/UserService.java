package ma.org.licence.pfe.services;

import ma.org.licence.pfe.dto.user.UserDto;
import ma.org.licence.pfe.entities.User;

import java.util.List;

public interface UserService {

    public List<UserDto> getAllUsers();
    public UserDto getUserById(String id);
    public UserDto getUserByEmail(String email);
    public User addUser(User user);
    void deleteUser(String id);
}
