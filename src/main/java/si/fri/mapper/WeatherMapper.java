package si.fri.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import si.fri.client.openmeteoapi.model.weather.WeatherResponse;
import si.fri.dto.WeatherDto;
import si.fri.entities.UserDataEntity;
import si.fri.entities.WeatherDataEntity;

@Mapper(componentModel = "cdi")
public interface WeatherMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    @Mapping(target = "rainfallmm", source = "response.current.rain")
    @Mapping(target = "etRef", source = "response.daily.et0_fao_evapotranspiration.first") // check if hourly should be used instead
    @Mapping(target = "humidity", source = "response.current.relative_humidity_2m")
    @Mapping(target = "temperature", source = "response.current.temperature_2m")
    @Mapping(target = "user", source = "user")
    WeatherDataEntity toEntity(WeatherResponse response, UserDataEntity user);

    WeatherDto toDto(WeatherDataEntity entity);
}