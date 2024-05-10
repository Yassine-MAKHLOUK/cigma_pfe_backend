package ma.org.licence.pfe.services;

import ma.org.licence.pfe.entities.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public User getUserById(String id);
    public User addUser(User user);
    void deleteUser(String id);
}
