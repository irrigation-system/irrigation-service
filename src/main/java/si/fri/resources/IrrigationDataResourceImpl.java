package si.fri.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import si.fri.dto.IrrigationDataDto;
import si.fri.service.IrrigationDataService;

@ApplicationScoped
public class IrrigationDataResourceImpl implements IrrigationDataResource {

    @Inject
    IrrigationDataService irrigationDataService;

    @Override
    public IrrigationDataDto getIrrigationData(String userToken) {

        try {
            return irrigationDataService.getIrrigationData(userToken);
        } catch (EntityNotFoundException e) {
            throw new WebApplicationException(
                    "User with token '" + userToken + "' does not exist.",
                    Response.Status.NOT_FOUND
            );
        }

    }
}
