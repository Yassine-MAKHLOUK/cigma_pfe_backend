package ma.org.licence.pfe.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.org.licence.pfe.models.DayTime;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetScheduleRequest {
    private String token;
    private List<DayTime> scheduleDays;
}
