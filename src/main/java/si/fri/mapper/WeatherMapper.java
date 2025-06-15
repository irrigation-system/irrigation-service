package si.fri.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import si.fri.client.openmeteoapi.model.WeatherResponse;
import si.fri.dto.WeatherDto;
import si.fri.entities.UserDataEntity;
import si.fri.entities.WeatherDataEntity;

@Mapper(componentModel = "cdi")
public interface WeatherMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    @Mapping(target = "rainfallmm", source = "response.hourly.rain.first")
    @Mapping(target = "etRef", source = "response.hourly.precipitation.first")
    @Mapping(target = "humidity", source = "response.hourly.relativehumidity_2m.first")
    @Mapping(target = "temperature", source = "response.hourly.temperature_2m.first")
    @Mapping(target = "user", source = "user")
    WeatherDataEntity toEntity(WeatherResponse response, UserDataEntity user);

    WeatherDto toDto(WeatherDataEntity entity);
}