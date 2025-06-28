package si.fri.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.fri.dto.SensorDataDto;

@Path("/api/v1/sensor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SensorDataResource {

    @GET
    SensorDataDto getLatestSensorData(@QueryParam("userToken") String userToken);
}
