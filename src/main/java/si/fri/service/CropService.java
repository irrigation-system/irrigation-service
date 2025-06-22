package si.fri.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import si.fri.dto.CropInfoDto;
import si.fri.entities.UserDataEntity;
import si.fri.mapper.CropMapper;

@ApplicationScoped
public class CropService {

    @Inject
    UserService userService;

    @Inject
    CropMapper mapper;

    public CropInfoDto getUserByToken(String userToken) {
        final UserDataEntity userByToken = userService.getUserByToken(userToken);
        return mapper.toDto(userByToken.getCropEntity());
    }
}
