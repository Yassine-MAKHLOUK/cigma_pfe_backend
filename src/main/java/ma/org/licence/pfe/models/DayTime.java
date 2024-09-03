package ma.org.licence.pfe.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.org.licence.pfe.enums.BarberState;
import ma.org.licence.pfe.enums.WeekDays;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayTime {
    @Enumerated(EnumType.STRING)
    private WeekDays day;
    private String startTime;
    private String endTime;
    @Enumerated(EnumType.STRING)
    private BarberState barberState;
}
