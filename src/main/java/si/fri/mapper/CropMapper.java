package si.fri.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import si.fri.dto.CropInfoDto;
import si.fri.entities.CropEntity;

@Mapper(componentModel = "cdi")
public interface CropMapper {

    /**
     * Maps both CropEntity and IrrigationDataEntity to a complete CropInfoDto
     */
    @Mapping(source = "crop.name", target = "name")
    @Mapping(source = "crop.minAllowedMoisture", target = "minAllowedMoisture")
    @Mapping(source = "crop.coefficientDev", target = "coefficientDev")
    @Mapping(source = "crop.coefficientMid", target = "coefficientMid")
    @Mapping(source = "crop.coefficientLate", target = "coefficientLate")
    @Mapping(source = "crop.devNumOfDays", target = "devNumOfDays")
    @Mapping(source = "crop.midNumOfDays", target = "midNumOfDays")
    @Mapping(source = "crop.latNumOfDays", target = "latNumOfDays")
    CropInfoDto toDto(CropEntity crop);

}
