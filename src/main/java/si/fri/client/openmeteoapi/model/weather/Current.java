package si.fri.client.openmeteoapi.model.weather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Current {
    private Float temperature_2m;
    private Float rain;
    private Integer relative_humidity_2m;
}
