package si.fri.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import si.fri.client.openmeteoapi.model.WeatherResponse;
import si.fri.entities.UserDataEntity;
import si.fri.entities.WeatherDataEntity;

import java.util.List;

@Mapper
public interface WeatherResponseToEntityMapper {

    WeatherResponseToEntityMapper INSTANCE = Mappers.getMapper(WeatherResponseToEntityMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    @Mapping(target = "rainfallmm", expression = "java(getFirst(response.getHourly().getRain()))")
    @Mapping(target = "etRef", expression = "java(getFirst(response.getHourly().getPrecipitation()))")
    @Mapping(target = "humidity", expression = "java(getFirst(response.getHourly().getRelativehumidity_2m()))")
    @Mapping(target = "temperature", expression = "java(getFirst(response.getHourly().getTemperature_2m()))")
    @Mapping(target = "user", source = "user")
    WeatherDataEntity mapResponseToEntity(WeatherResponse response, UserDataEntity user);

    default Float getFirst(List<Float> list) {
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

}
