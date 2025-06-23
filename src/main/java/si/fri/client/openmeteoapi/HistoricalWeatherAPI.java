package si.fri.client.openmeteoapi;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import si.fri.client.openmeteoapi.model.historical.HistoricalWeatherResponse;

@Path("v1/archive")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey="openmeteo-historical-api")
public interface HistoricalWeatherAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    HistoricalWeatherResponse getHistoricalWeatherData(@QueryParam("latitude") @NotNull double latitude,
                                                       @QueryParam("longitude") @NotNull double longitude,
                                                       @QueryParam("start_date") String startDate,
                                                       @QueryParam("end_date") String endDate,
                                                       @QueryParam("daily") String daily,
                                                       @QueryParam("precipitation_unit") String precipitationUnit,
                                                       @QueryParam("timeformat") String timeformat,
                                                       @QueryParam("timezone") String timezone);

}
