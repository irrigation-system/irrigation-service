package si.fri.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import si.fri.dto.CropInfoDto;
import si.fri.service.CropService;


@ApplicationScoped
public class CropResourceImpl implements CropResource {

    @Inject
    CropService cropService;

    @Override
    public CropInfoDto getCropInfo(String userToken) {
        return cropService.getUserByToken(userToken);
    }

    // TODO POST get all crops(names and ids)

}
