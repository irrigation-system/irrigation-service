package si.fri.service;

import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.fri.client.openmeteoapi.WeatherForecastAPI;
import si.fri.client.openmeteoapi.model.WeatherResponse;
import si.fri.dto.WeatherDto;
import si.fri.entities.UserDataEntity;

import java.util.List;

public class WeatherService {

    @Inject
    @RestClient
    WeatherForecastAPI weatherForecastAPI;

    public WeatherDto getWeatherForecastForUser(UserDataEntity user) {

        try {
            // TODO implement fetching of weather data
            // WeatherResponse response = fetchFromWeatherAPI(lat, lon);
            // WeatherDataEntity entity = WeatherResponseToEntityMapper.INSTANCE.mapResponseToEntity(response, user);

            // add user to weather
            // weatherRepository.save(entity);

            // WeatherDto weatherDto = mapResponseToDto(response);

            //return weatherDto;

        } catch (Exception ex) { // if no
            //WeatherEntity lastKnown = weatherRepository.findLatestByLocation(lat, lon);
            //if (lastKnown == null) {
            //    throw new RuntimeException("No weather data available for location: " + lat + ", " + lon);
            //}
            //return mapEntityToDto(lastKnown);
        }


        return null;
    }


    private WeatherResponse fetchFromWeatherAPI(float lat, float lon) {
        List<String> hourlyParameters = List.of("temperature_2m",
                "relativehumidity_2m",
                "apparent_temperature",
                "precipitation",
                "rain",
                "showers",
                "snowfall",
                "snow_depth",
                "weathercode",
                "surface_pressure",
                "cloudcover",
                "visibility",
                "windspeed_10m",
                "windgusts_10m",
                "soil_moisture_0_1cm",
                "is_day");

        List<String> dailyParameters = List.of("weathercode",
                "sunrise",
                "sunset",
                "precipitation_hours");

        return weatherForecastAPI.getWeatherData(lat,
                lon,
                String.join(",", hourlyParameters),
                String.join(",", dailyParameters),
                false,
                "celsius",
                "kmh",
                "mm",
                "iso8601",
                "auto",
                1,
                1,
                null,
                null);
    }
}
