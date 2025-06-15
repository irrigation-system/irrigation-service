package si.fri.resources;

import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import si.fri.dto.WeatherDto;
import si.fri.entities.UserDataEntity;
import si.fri.service.UserService;
import si.fri.service.WeatherService;

public class WeatherResourceImpl implements WeatherResource {

    @Inject
    WeatherService weatherService;

    @Inject
    UserService userService;

    @Override
    public WeatherDto getCurrentWeatherForecast(String userToken) {

        UserDataEntity userDataEntity;

        try {
            userDataEntity = userService.getUserByToken(userToken);
        } catch (NoResultException e) {
            throw new WebApplicationException(
                    "User with token '" + userToken + "' does not exist.",
                    Response.Status.NOT_FOUND
            );
        }

        weatherService.getWeatherForecastForUser(userDataEntity);



        return null;
    }
}
