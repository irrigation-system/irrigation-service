package si.fri.service;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.fri.client.openmeteoapi.HistoricalWeatherAPI;
import si.fri.client.openmeteoapi.WeatherForecastAPI;
import si.fri.client.openmeteoapi.model.historical.HistoricalWeatherResponse;
import si.fri.client.openmeteoapi.model.weather.WeatherResponse;
import si.fri.dto.WeatherDto;
import si.fri.entities.UserDataEntity;
import si.fri.entities.WeatherDataEntity;
import si.fri.mapper.WeatherMapper;
import si.fri.repositories.WeatherRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class WeatherService {

    @Inject
    @RestClient
    WeatherForecastAPI weatherForecastAPI;

    @Inject
    @RestClient
    HistoricalWeatherAPI historicalWeatherAPI;

    @Inject
    WeatherRepository weatherRepository;

    @Inject
    UserService userService;

    @Inject
    WeatherMapper mapper;

    public WeatherDto getWeatherForecastForUser(String userToken) {

        UserDataEntity user = userService.getUserByToken(userToken);

        try {
            WeatherResponse response = fetchFromWeatherAPI(user.getLocation().getLatitude(), user.getLocation().getLongitude());
            WeatherDataEntity entity = mapper.toEntity(response, user);

            weatherRepository.saveForUser(entity);
            return mapper.toDto(entity);

        } catch (Exception ex) {
            Log.error("Failed to fetch weather data for user: " + user.getUserToken(), ex);
            WeatherDataEntity lastKnown = weatherRepository.findLatest(user);
            if (lastKnown == null) {
                throw new NotFoundException("No weather data available for location: " + user.getLocation() );
            }
            return mapper.toDto(lastKnown);
        }
    }


    private WeatherResponse fetchFromWeatherAPI(float lat, float lon) {
        List<String> hourlyParameters = List.of("temperature_2m",
                "relativehumidity_2m",
                "precipitation",
                "rain",
                "soil_moisture_0_1cm",
                "is_day",
                "et0_fao_evapotranspiration");

        List<String> dailyParameters = List.of("weathercode",
                "sunrise",
                "sunset",
                "precipitation_hours",
                "et0_fao_evapotranspiration");

        List<String> currentParameters = List.of("temperature_2m",
                "rain",
                "relative_humidity_2m");

        return weatherForecastAPI.getWeatherData(lat,
                lon,
                String.join(",", hourlyParameters),
                String.join(",", dailyParameters),
                String.join(",", currentParameters),
                "celsius",
                "kmh",
                "mm",
                "iso8601",
                "auto",
                1,
                0,
                null,
                null);
    }


    public Float fetchHistoricalRainfallData(UserDataEntity userDataEntity, LocalDate startDate, LocalDate endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        List<String> dailyParameters = List.of("rain_sum", "precipitation_sum");

        HistoricalWeatherResponse response = historicalWeatherAPI.getHistoricalWeatherData(
                userDataEntity.getLocation().getLatitude(),
                userDataEntity.getLocation().getLongitude(),
                startDate.format(formatter),
                endDate.format(formatter),
                String.join(",", dailyParameters),
                "mm",
                "iso8601",
                "Europe/Berlin"
        );

        if (response == null || response.getDaily() == null) return 0f;

        List<Float> rainValues = response.getDaily().getRain_sum();
        if (rainValues == null || rainValues.isEmpty()) return 0f;

        return (float) rainValues.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Float::doubleValue)
                .sum();

    }

}