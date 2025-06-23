package si.fri.client.openmeteoapi.model.weather;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Daily {
    private List<String> sunrise;
    private List<Integer> weather_code;
    private List<String> sunset;
    private List<String> time;
    private List<Float> precipitation_hours;
    private List<Float> et0_fao_evapotranspiration;
}
