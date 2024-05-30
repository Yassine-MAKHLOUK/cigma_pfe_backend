package ma.org.licence.pfe.controllers;


import lombok.RequiredArgsConstructor;
import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.entities.User;
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

    //@PostMapping()

    //@PutMapping()

    //@DeleteMapping

}
