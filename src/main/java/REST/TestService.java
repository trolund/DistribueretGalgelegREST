package REST;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/Test.test")
public class TestService {

    @GET
    @Path("/Test.test")
    @Produces(MediaType.TEXT_PLAIN)
    public String Test() {
        return "Test.test virker";
    }

}
