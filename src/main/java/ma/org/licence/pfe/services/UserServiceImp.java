package ma.org.licence.pfe.services;

import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImp implements UserService {


    @Autowired
    UserRepository userRepository;



    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public User addUser(User user) {
        User userToAdd = userRepository.save(user);
        return userToAdd;
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
