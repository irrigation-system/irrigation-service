package si.fri.client.openmeteoapi.model.weather;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Hourly {
    private List<Float> windgusts_10m;
    private List<Float> rain;
    private List<Float> surface_pressure;
    private List<Float> visibility;
    private List<Float> snowfall;
    private List<Integer> is_day;
    private List<Integer> cloudcover;
    private List<Float> temperature_2m;
    private List<Integer> relativehumidity_2m;
    private List<Float> precipitation;
    private List<Float> showers;
    private List<Integer> weathercode;
    private List<Float> soil_moisture_0_1cm;
    private List<Float> apparent_temperature;
    private List<Float> windspeed_10m;
    private List<String> time;
    private List<Float> snow_depth;
    private List<Float> et0_fao_evapotranspiration;

}
