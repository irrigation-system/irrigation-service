package si.fri.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import si.fri.dto.IrrigationDataDto;
import si.fri.entities.UserDataEntity;
import si.fri.service.IrrigationDataService;
import si.fri.service.UserService;

@ApplicationScoped
public class IrrigationDataResourceImpl implements IrrigationDataResource {

    @Inject
    IrrigationDataService irrigationDataService;

    @Inject
    UserService userService;

    @Transactional
    @Override
    public IrrigationDataDto getIrrigationData(String userToken) {
        UserDataEntity userDataEntity;

        try {
            userDataEntity = userService.getUserByToken(userToken);
        } catch (NoResultException e) {
            throw new WebApplicationException(
                    "User with token '" + userToken + "' does not exist.",
                    Response.Status.NOT_FOUND
            );
        }

        return irrigationDataService.getIrrigationData(userDataEntity);
    }
}
