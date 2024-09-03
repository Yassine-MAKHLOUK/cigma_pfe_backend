package ma.org.licence.pfe.controllers;

import lombok.RequiredArgsConstructor;
import ma.org.licence.pfe.entities.Barber;
import ma.org.licence.pfe.exceptions.BadRequestException;
import ma.org.licence.pfe.requests.BarberPrestationRequest;
import ma.org.licence.pfe.requests.SetAdressRequest;
import ma.org.licence.pfe.requests.SetScheduleRequest;
import ma.org.licence.pfe.response.ResponseHandler;
import ma.org.licence.pfe.requests.BarberRegisterRequest;
import ma.org.licence.pfe.services.BarberServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/barber")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BarberController {

    private  final BarberServiceImp barberServiceImp;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        try {
            List<Barber> data = barberServiceImp.getAllBarbers();
            return ResponseHandler.generateResponse("Data retrieved successfully!", HttpStatus.OK, data);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/barberRegister")
    public ResponseEntity<Object> barberRegister(@RequestBody BarberRegisterRequest request) throws BadRequestException {
        try {
            return ResponseHandler.generateResponse("Barber Added successfully!", HttpStatus.OK, barberServiceImp.barberRegister(request));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/addPrestation")
    public ResponseEntity<Object> addBarberPrestation(@RequestBody BarberPrestationRequest request) throws BadRequestException {
        try {
            return ResponseHandler.generateResponse("Barber Added successfully!", HttpStatus.OK, barberServiceImp.addBarberPrestation(request));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/barberAddress")
    public ResponseEntity<Object> setBarberAddress(@RequestBody SetAdressRequest request) throws BadRequestException {
        try {
            return ResponseHandler.generateResponse("Address Added successfully!", HttpStatus.OK, barberServiceImp.setBarberAddress(request.getToken(), request));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/barberSchedule")
    public ResponseEntity<Object> setBarberSchedule(@RequestBody SetScheduleRequest request) throws BadRequestException {
        try {
            return ResponseHandler.generateResponse("Schedule Added successfully!", HttpStatus.OK, barberServiceImp.setBarberSchedule(request.getToken(), request));
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
