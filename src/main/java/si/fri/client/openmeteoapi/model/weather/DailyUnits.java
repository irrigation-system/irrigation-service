package si.fri.client.openmeteoapi.model.weather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyUnits {
    private String sunrise;
    private String weather_code;
    private String sunset;
    private String time;
    private String precipitation_hours;
}
