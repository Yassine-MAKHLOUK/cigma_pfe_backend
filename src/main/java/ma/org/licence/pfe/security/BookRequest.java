package ma.org.licence.pfe.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private String BarberName;
    private String token;
    private String date;
    private String service;
}
