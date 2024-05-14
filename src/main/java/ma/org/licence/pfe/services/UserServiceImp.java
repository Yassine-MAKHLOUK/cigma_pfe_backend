package ma.org.licence.pfe.services;

import ma.org.licence.pfe.dto.user.UserDto;
import ma.org.licence.pfe.dto.user.UserDtoMapper;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.exceptions.ResourceNotFoundException;
import ma.org.licence.pfe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDtoMapper userDtoMapper;



    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDtoMapper)
                .collect(Collectors.toList());

    }

    @Override
    public UserDto getUserById(String id) {
        return userRepository.findById(id)
                .map(userDtoMapper).get();
    }

    @Override
    public void addUser(User user) {

        userRepository.save(user);

    }

    @Override
    public void deleteUser(String id) {
        checkIfUserExistsOrThrow(id);
        userRepository.deleteById(id);
    }
    private void checkIfUserExistsOrThrow(String userId) {
        UserDto user = getUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException(
                    "User with id [%s] not found".formatted(userId)
            );
        }
    }
}
