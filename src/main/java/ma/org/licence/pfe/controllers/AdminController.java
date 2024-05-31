package ma.org.licence.pfe.controllers;


import lombok.RequiredArgsConstructor;
import ma.org.licence.pfe.dto.user.UserDto;
import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.User;
import ma.org.licence.pfe.exceptions.BadRequestException;
import ma.org.licence.pfe.requests.BarberRegisterRequest;
import ma.org.licence.pfe.requests.UserAddRequest;
import ma.org.licence.pfe.response.ResponseHandler;
import ma.org.licence.pfe.services.AdminServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    private final AdminServiceImp adminServiceImp;


    @GetMapping("/users/all")
    public ResponseEntity<Object> getAllUsers() {
        try {
            List<User> data = adminServiceImp.getAllUsers();
            return ResponseHandler.generateResponse("Data retrieved successfully!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<Object> getUser(@PathVariable String email) {
        try {
            User data = adminServiceImp.getUser(email);
            return ResponseHandler.generateResponse("Data retrieved successfully!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users/add")
    public ResponseEntity<Object> addUser(@RequestBody UserAddRequest request) throws BadRequestException {
        try {
            return ResponseHandler.generateResponse("User Added successfully!", HttpStatus.OK, adminServiceImp.addUser(request));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/users/update/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable String id) throws BadRequestException {
        try {
            return ResponseHandler.generateResponse("User updated successfully!", HttpStatus.OK, adminServiceImp.updateUser(id, user));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    //@DeleteMapping

}