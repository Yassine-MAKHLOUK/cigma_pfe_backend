package ma.org.licence.pfe.controllers;


import lombok.RequiredArgsConstructor;
import ma.org.licence.pfe.response.ResponseHandler;
import ma.org.licence.pfe.requests.BookRequest;
import ma.org.licence.pfe.services.BookingServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    private final BookingServiceImp bookingService;

    @PostMapping("/clientBook")
    public ResponseEntity<Object> book(@RequestBody BookRequest request) {
        try {

            return ResponseHandler.generateResponse("User created successfully!", HttpStatus.CREATED, bookingService.clientBook(request));
        }catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/user/{token}")
    public ResponseEntity<Object> getBookByUsername(@PathVariable String token) {
        try {

            return ResponseHandler.generateResponse("Bookings retrieved successfully!", HttpStatus.OK, bookingService.getBookByUser(token));
        }catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/barber/{token}")
    public ResponseEntity<Object> getBookBybarberName(@PathVariable String token) {
        try {

            return ResponseHandler.generateResponse("Bookings retrieved successfully!", HttpStatus.OK, bookingService.getBookByBarber(token));
        }catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
