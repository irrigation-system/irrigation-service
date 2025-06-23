package si.fri.client.openmeteoapi.model.historical;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoricalWeatherResponse {
    private Daily daily;
}
