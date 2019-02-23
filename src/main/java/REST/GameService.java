package REST;

import Controller.MainController;
import POJO.LoginFormPOJO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.rmi.RemoteException;

@Path("/game")
public class GameService {

    private MainController controller = MainController.getInstance();

    @GET
    public String getTest(){
        return "<p>Du bliver nød til at specificere hvilke resurse du ønsker. \n" +
                "kunne det være:</p>\n" +
                "<p>for at få syndligt ord: GET </p><a href=\"game/synligtord\">game/synligtord</a>" +
                "<p>for at gætte er bogstav: POST </p><a href=\"game/geatbogstav\">game/geatbogstav</a>" +
                "<p>AntalForkerteBogstaver : POST </p><a href=\"game/AntalForkerteBogstaver\">game/AntalForkerteBogstaver</a>";
    }

    @GET
    @Path("/synligtord")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVisabelWord()  {
        try {
            return controller.getVisabelWord();
        } catch (RemoteException e) {
            e.printStackTrace();
            return "";
        }
    }

    @POST
    @Path("/geatbogstav")
    public Boolean gessChar(@QueryParam("letter") String letter)  {
        try {
            return controller.gess(letter);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @GET
    @Path("/getAntalForkerteBogstaver")
    @Produces(MediaType.TEXT_PLAIN)
    public int getLife()  {
        try {
            return controller.getlife();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*

    @GET
    @Path("/getAntalForkerteBogstaver")
    @Produces(MediaType.TEXT_PLAIN)
    public int getLife()  {
        try {
            return controller.getlife();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }
*/
}