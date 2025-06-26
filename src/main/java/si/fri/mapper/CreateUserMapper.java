package si.fri.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import si.fri.dto.CreateUserDto;
import si.fri.entities.UserDataEntity;

@Mapper(componentModel = "cdi")
public interface CreateUserMapper {

    @Mapping(target = "location.latitude", source = "lat")
    @Mapping(target = "location.longitude", source = "lng")
    @Mapping(target = "irrigationEntity.cultivationArea", source = "cultivationArea")
    UserDataEntity toEntity(CreateUserDto user);

}
