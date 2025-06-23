package si.fri.client.openmeteoapi.model.weather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HourlyUnits {
    private String windgusts_10m;
    private String rain;
    private String surface_pressure;
    private String visibility;
    private String snowfall;
    private String is_day;
    private String cloudcover;
    private String temperature_2m;
    private String relativehumidity_2m;
    private String precipitation;
    private String showers;
    private String weathercode;
    private String soil_moisture_0_1cm;
    private String apparent_temperature;
    private String windspeed_10m;
    private String time;
    private String snow_depth;
}
