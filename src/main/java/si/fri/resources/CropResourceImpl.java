package si.fri.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import si.fri.dto.CropDto;
import si.fri.dto.CropInfoDto;
import si.fri.service.CropService;

import java.util.List;


@ApplicationScoped
public class CropResourceImpl implements CropResource {

    @Inject
    CropService cropService;

    @Override
    public CropInfoDto getCropInfo(String userToken) {
        return cropService.getUserByToken(userToken);
    }

    @Override
    public List<CropDto> getAllCrops() {
        return cropService.getAllCrops();
    }

}
