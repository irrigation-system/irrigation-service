package si.fri.mapper;

import org.mapstruct.Mapper;
import si.fri.dto.SensorDataDto;
import si.fri.entities.SensorDataEntity;

@Mapper(componentModel = "cdi")
public interface SensorDataMapper {

    SensorDataDto toDto(SensorDataEntity sensorDataEntity);
}
