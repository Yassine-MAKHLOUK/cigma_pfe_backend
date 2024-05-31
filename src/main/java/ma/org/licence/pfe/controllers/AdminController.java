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


    /***
     *Get Mapping
     */
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
            return ResponseHandler.generateResponse("User retrieved successfully!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/barbers/all")
    public ResponseEntity<Object> getBarbers() {
        try {
            List<Barber> data = adminServiceImp.getAllBarbers();
            return ResponseHandler.generateResponse("Data retrieved successfully!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/barbers/{barberName}")
    public ResponseEntity<Object> getBarber(@PathVariable String barberName) {
        try {
            Barber data = adminServiceImp.getBarber(barberName);
            return ResponseHandler.generateResponse("Barber retrieved successfully!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



    /***
     *Post Mapping
     */
    @PostMapping("/users/add")
    public ResponseEntity<Object> addUser(@RequestBody UserAddRequest request) throws BadRequestException {
        try {
            return ResponseHandler.generateResponse("User Added successfully!", HttpStatus.CREATED, adminServiceImp.addUser(request));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/barbers/add")
    public ResponseEntity<Object> addBarber(@RequestBody BarberRegisterRequest request) throws BadRequestException {
        try {
            return ResponseHandler.generateResponse("Barber Added successfully!", HttpStatus.CREATED, adminServiceImp.addBarber(request));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }



    /***
     *Put Mapping
     */
    @PutMapping("/users/update/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable String id) throws BadRequestException {
        try {
            return ResponseHandler.generateResponse("User updated successfully!", HttpStatus.OK, adminServiceImp.updateUser(id, user));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/barbers/update/{barberName}")
    public ResponseEntity<Object> updateUser(@RequestBody Barber barber, @PathVariable String barberName) throws BadRequestException {
        try {
            return ResponseHandler.generateResponse("Barber updated successfully!", HttpStatus.OK, adminServiceImp.updateBarber(barberName, barber));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }



    /***
     *Delete Mapping
     */
    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) throws BadRequestException {
        try {
            adminServiceImp.deleteUser(id);
            return ResponseHandler.generateResponse("User deleted successfully!", HttpStatus.NO_CONTENT, null);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
