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
    @Mapping(source = "response.current.rain", target = "rainfallmm")
    @Mapping(source = "response.daily.et0_fao_evapotranspiration.first", target = "etRef")
    @Mapping(source = "response.current.relative_humidity_2m", target = "humidity")
    @Mapping(source = "response.current.temperature_2m", target = "temperature")
    @Mapping(source = "user", target = "user")
    WeatherDataEntity toEntity(WeatherResponse response, UserDataEntity user);

    WeatherDto toDto(WeatherDataEntity entity);
}