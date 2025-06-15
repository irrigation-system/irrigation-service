package si.fri.client.openmeteoapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private Float elevation;
    private HourlyUnits hourly_units;
    private Object generationtime_ms;
    private String timezone_abbreviation;
    private DailyUnits dailyunits;
    private String timezone;
    private Float latitude;
    private Daily daily;
    private int utc_offset_seconds;
    private Hourly hourly;
    private Float longitude;
    private Current current;

}
