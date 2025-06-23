package si.fri.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.fri.dto.IrrigationDataDto;

@Path("/api/v1/irrigation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IrrigationDataResource {

    @GET
    IrrigationDataDto getIrrigationData(@QueryParam("userToken") String userToken);

}
