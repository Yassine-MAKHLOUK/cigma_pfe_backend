package ma.org.licence.pfe.controllers;

import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.repositories.UserRepository;
import ma.org.licence.pfe.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {


    @Autowired
    UserServiceImp userServiceImp;



    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServiceImp.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userServiceImp.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User addeduser = userServiceImp.addUser(user);
        return  new ResponseEntity<>(addeduser, HttpStatus.CREATED);

    }
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User olduser = userServiceImp.getUserById(id);
        User newuser = user;
        return  new ResponseEntity<>(newuser, HttpStatus.OK);

    }
    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userServiceImp.deleteUser(id);
        return  new ResponseEntity<>( HttpStatus.OK);

    }

}
