package si.fri.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.QueryParam;
import si.fri.dto.SensorDataDto;
import si.fri.service.SensorDataService;

@ApplicationScoped
public class SensorDataResourceImpl implements SensorDataResource {

    @Inject
    SensorDataService sensorDataService;

    @Override
    public SensorDataDto getLatestSensorData(@QueryParam("userToken") String userToken) {
        return sensorDataService.getLatest(userToken);
    }
}
