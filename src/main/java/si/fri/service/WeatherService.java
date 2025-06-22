package si.fri.service;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.fri.client.openmeteoapi.WeatherForecastAPI;
import si.fri.client.openmeteoapi.model.WeatherResponse;
import si.fri.dto.IrrigationDataDto;
import si.fri.dto.WeatherDto;
import si.fri.entities.IrrigationDataEntity;
import si.fri.entities.UserDataEntity;
import si.fri.entities.WeatherDataEntity;
import si.fri.mapper.IrrigationMapper;
import si.fri.mapper.WeatherMapper;
import si.fri.repositories.WeatherRepository;

import java.util.List;

@ApplicationScoped
public class WeatherService {

    @Inject
    @RestClient
    WeatherForecastAPI weatherForecastAPI;

    @Inject
    WeatherRepository weatherRepository;

    @Inject
    WeatherMapper mapper;

    @Inject
    IrrigationMapper irrigationMapper;


    public WeatherDto getWeatherForecastForUser(UserDataEntity user) {

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


    private si.fri.client.openmeteoapi.model.WeatherResponse fetchFromWeatherAPI(float lat, float lon) {
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

    public IrrigationDataDto getIrrigationData(UserDataEntity userDataEntity) {
        final IrrigationDataEntity irrigationEntity = userDataEntity.getIrrigationEntity();

        // Check if an irrigation entity exists
        if (irrigationEntity == null) {
            throw new IllegalStateException("Irrigation data not found for user");
        }

        // Check if rainfall data is already set
        if (irrigationEntity.getMonthlyRainfall() == null || irrigationEntity.getMonthlyRainfallMonth() == null) {
            // Fetch historical rainfall data
            Float monthlyRainfall = fetchHistoricalRainfallData(userDataEntity);

            // Update irrigation entity with fetched data
            irrigationEntity.setMonthlyRainfall(monthlyRainfall);
            irrigationEntity.setMonthlyRainfallMonth(getCurrentMonth());

            // need to save the updated entity here but I think it is managed if not the whole flow is incorrect
//             irrigationRepository.save(irrigationEntity);
        }

        return irrigationMapper.toDto(irrigationEntity);
    }


    private Float fetchHistoricalRainfallData(UserDataEntity userDataEntity) {
        //todo
        // This would require creating a new REST client for historical weather API
//            userDataEntity.getLocation().getLatitude();
//            userDataEntity.getLocation().getLongitude();
//            start and end are current motnth but for last year

        // The API call would be something like:
        // GET https://archive-api.open-meteo.com/v1/archive
        // ?latitude={lat}&longitude={lon}&start_date={start}&end_date={end}
        // &daily=rain_sum&timezone=Europe/Berlin

        Log.warn("Historical weather data fetching not yet implemented");
        return 0.0f; // Placeholder
    }

    private String getCurrentMonth() {
        return java.time.LocalDate.now().getMonth().toString();
    }
}