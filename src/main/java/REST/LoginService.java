package REST;

import Controller.MainController;
import POJO.LoginFormPOJO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@Path("/login")
public class LoginService {

    MainController controller = MainController.getInstance();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
 //   @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginFormPOJO formPOJO)  {
        System.out.println(formPOJO.getPassword() + "trying to login!");
        try {
            controller.login(formPOJO.getUsername(), formPOJO.getPassword());
            return Response.status(200).entity(formPOJO.getUsername() + " : logged ind successfuldt").build();
        } catch (RemoteException e) {
            e.printStackTrace();
            return Response.status(401).entity(formPOJO.getUsername() + " : fejled ved login." + e.getMessage()).build();
        } catch (NotBoundException e) {
            e.printStackTrace();
            return Response.status(401).entity(formPOJO.getUsername() + " : fejled ved login." + e.getMessage()).build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return Response.status(401).entity(formPOJO.getUsername() + " : fejled ved login." + e.getMessage()).build();
        }
    }


    /*

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

     */
}
