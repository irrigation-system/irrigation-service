
package si.fri.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import si.fri.dto.IrrigationDataDto;
import si.fri.entities.IrrigationDataEntity;

@Mapper(componentModel = "cdi")
public interface IrrigationMapper {

    /**
     * Maps IrrigationDataEntity to IrrigationDataDto
     */
    @Mapping(source = "irrigationStart", target = "irrigationStart")
    @Mapping(source = "monthlyRainfallMonth", target = "monthlyRainfallMonth")
    @Mapping(source = "monthlyRainfall", target = "monthlyRainfall")
    @Mapping(source = "cultivationArea", target = "cultivationArea")
    IrrigationDataDto toDto(IrrigationDataEntity irrigationEntity);
}