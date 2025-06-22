package si.fri.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.fri.dto.CropInfoDto;

@Path("/api/v1/crop")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CropResource {

    @GET
    CropInfoDto getCropInfo(@QueryParam("userToken") String userToken);

}