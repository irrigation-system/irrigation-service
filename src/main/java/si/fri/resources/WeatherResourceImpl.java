package si.fri.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import si.fri.dto.WeatherDto;
import si.fri.service.WeatherService;

@ApplicationScoped
public class WeatherResourceImpl implements WeatherResource {

    @Inject
    WeatherService weatherService;

    @Override
    public WeatherDto getCurrentWeatherForecast(String userToken) {

        try {
            return weatherService.getWeatherForecastForUser(userToken);
        } catch (EntityNotFoundException e) {
            throw new WebApplicationException(
                    "User with token '" + userToken + "' does not exist.",
                    Response.Status.NOT_FOUND
            );
        }
    }

}
