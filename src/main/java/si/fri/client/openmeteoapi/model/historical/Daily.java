package si.fri.client.openmeteoapi.model.historical;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Daily {
    private List<LocalDate> time;
    private List<Float> rain_sum;
    private List<Float> precipitation_sum;
}
