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

    private final UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        try {
            List<UserDto> data = userService.getAllUsers();
            return ResponseHandler.generateResponse("Data retrieved successfully!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id) {
        try {
            UserDto user = userService.getUserById(id);
            return ResponseHandler.generateResponse("User retrieved successfully!", HttpStatus.OK, user);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseHandler.generateResponse("User created successfully!", HttpStatus.CREATED, null);
        }catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseHandler.generateResponse("User deleted successfully!", HttpStatus.NO_CONTENT, null);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}