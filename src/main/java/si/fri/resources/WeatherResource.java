package si.fri.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.fri.dto.IrrigationDataDto;
import si.fri.dto.WeatherDto;

@Path("/api/v1/weather")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface WeatherResource {

    @GET
    WeatherDto getCurrentWeatherForecast(@QueryParam("userToken") String userToken);

    @GET
    @Path("/irrigation")
    IrrigationDataDto getIrrigationData(@QueryParam("userToken") String userToken);


}




