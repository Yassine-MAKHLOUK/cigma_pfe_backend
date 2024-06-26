package ma.org.licence.pfe.services;

import ma.org.licence.pfe.dto.user.UserDto;
import ma.org.licence.pfe.dto.user.UserDtoMapper;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.exceptions.BadRequestException;
import ma.org.licence.pfe.exceptions.ResourceNotFoundException;
import ma.org.licence.pfe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userRepository.findById(id)
                    .map(userDtoMapper).get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("User with id [%s] not found", id)
            );
        }
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return userRepository.findById(email)
                    .map(userDtoMapper).get();
        } else {
            throw new ResourceNotFoundException(
                    String.format("User with email [%s] not found", email)
            );
        }
    }

    @Override
    public User addUser(User user) throws BadRequestException {
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new BadRequestException("User already Exist");
        }else {
            userRepository.save(user);
            return user;
        }
    }

    @Override
    public void deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(
                    String.format("User with id [%s] not found", id)
            );
        }
    }

}
