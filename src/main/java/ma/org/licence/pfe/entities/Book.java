package ma.org.licence.pfe.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.org.licence.pfe.enums.BookingStatus;
import ma.org.licence.pfe.models.BarberPrestation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "booking")
public class Book {
    @Id
    private String bookingId;
    private User user;
    private Barber barber;
    private LocalDate bookingTime;
    private BarberPrestation barberPrestation;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private Date createdAt;
    private Date updatedAt;
}
