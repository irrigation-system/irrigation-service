package si.fri.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import si.fri.dto.CropDto;
import si.fri.dto.CropInfoDto;
import si.fri.entities.UserDataEntity;
import si.fri.mapper.CropMapper;
import si.fri.repositories.CropRepository;

import java.util.List;

@ApplicationScoped
public class CropService {

    @Inject
    private CropRepository cropRepository;

    @Inject
    UserService userService;

    @Inject
    CropMapper mapper;

    public CropInfoDto getUserByToken(String userToken) {
        final UserDataEntity userByToken = userService.getUserByToken(userToken);
        return mapper.toDto(userByToken.getCropEntity());
    }

    public List<CropDto> getAllCrops() {
        return cropRepository.findAll().stream()
                .map(c -> new CropDto(c.getId().toString(), c.getName()))
                .toList();
    }
}
