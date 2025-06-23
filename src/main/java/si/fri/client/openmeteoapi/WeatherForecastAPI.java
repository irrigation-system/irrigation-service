package si.fri.client.openmeteoapi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import si.fri.client.openmeteoapi.model.weather.WeatherResponse;

@Path("v1/forecast")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey="openmeteo-api")
public interface WeatherForecastAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    WeatherResponse getWeatherData(@QueryParam("latitude") @NotNull Float latitude,
                                   @QueryParam("longitude") @NotNull Float longitude,
                                   @QueryParam("hourly") @Valid String hourly,
                                   @QueryParam("daily") @Valid String daily,
                                   @QueryParam("current") @Valid String current,
                                   @QueryParam("temperature_unit") String temperatureUnit,
                                   @QueryParam("windspeed_unit") String windspeedUnit,
                                   @QueryParam("precipitation_unit") String precipitationUnit,
                                   @QueryParam("timeformat") String timeformat,
                                   @QueryParam("timezone") String timezone,
                                   @QueryParam("forecast_days") Integer forecastDays,
                                   @QueryParam("past_days") Integer pastDays,
                                   @QueryParam("start_date") Integer startDate,
                                   @QueryParam("end_date") Integer endDate);

}
