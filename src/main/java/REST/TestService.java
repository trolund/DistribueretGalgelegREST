package REST;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/Test")
public class TestService {

    @GET
    @Path("/Test")
    @Produces(MediaType.TEXT_PLAIN)
    public String Test() {
        return "Test virker";
    }
}
