package REST;

import Controller.MainController;
import POJO.LoginFormPOJO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginService {

    MainController controller = MainController.getInstance();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(LoginFormPOJO formPOJO)  {
        System.out.println(formPOJO.getPassword() + "trying to login!");
        if(controller.login(formPOJO.getUsername(), formPOJO.getPassword())){
            return formPOJO.getUsername() + " : logged in successfully.";
        }else {
            return "error";
        }
    }

}
