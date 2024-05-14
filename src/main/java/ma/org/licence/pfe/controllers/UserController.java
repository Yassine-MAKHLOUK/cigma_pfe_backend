package ma.org.licence.pfe.controllers;

import ma.org.licence.pfe.dto.user.UserDto;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.exceptions.UserNotFoundException;
import ma.org.licence.pfe.response.ResponseHandler;
import ma.org.licence.pfe.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserServiceImp userServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        List<UserDto> data = userServiceImp.getAllUsers();
        return ResponseHandler.generateResponse("Data retrieved successfully!", HttpStatus.OK, data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id) {
        try {
            UserDto user = userServiceImp.getUserById(id);
            return ResponseHandler.generateResponse("User retrieved successfully!", HttpStatus.OK, user);
        } catch (UserNotFoundException e) {
            return ResponseHandler.generateErrorResponse("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        userServiceImp.addUser(user);
        return ResponseHandler.generateResponse("User created successfully!", HttpStatus.CREATED, null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        userServiceImp.deleteUser(id);
        return ResponseHandler.generateResponse("User deleted successfully!", HttpStatus.NO_CONTENT, null);
    }
}