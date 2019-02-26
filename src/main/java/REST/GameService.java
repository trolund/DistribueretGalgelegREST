package REST;

import Controller.MainController;
import POJO.LoginFormPOJO;
import SOAP.ISOAPAdapter;
import SOAP.SOAPAdapter;
import SOAP.SOAPServer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.Endpoint;
import java.rmi.RemoteException;

@Path("/game")
public class GameService {

    private MainController controller = MainController.getInstance();


    @GET
    public String getDiscription(){
        return "<p>Du bliver nød til at specificere hvilke resurse du ønsker. \n" +
                "kunne det være:</p>\n" +
                "<p>for at få syndligt ord: GET </p><a href=\"game/synligtord\">game/synligtord</a>" +
                "<p>for at gætte er bogstav: POST </p><a href=\"game/geatbogstav\">game/geatbogstav</a>" +
                "<p>AntalForkerteBogstaver : POST </p><a href=\"game/AntalForkerteBogstaver\">game/AntalForkerteBogstaver</a>";
    }

    @POST
    @Path("/newGame")
    public boolean startNewGame(@QueryParam("userid") String userid)  {
        return controller.newGame(userid);
    }

    @POST
    @Path("/forceNewGame")
    public boolean forceNewGame(@QueryParam("userid") String userid)  {
        try {
            controller.forceNewGame(userid);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @POST
    @Path("/forceDeleteGame")
    public boolean deleteGame(@QueryParam("userid") String userid)  {
        try {
            controller.deleteGame(userid);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @GET
    @Path("/synligtord")
    @Produces(MediaType.TEXT_PLAIN)
    public String getVisabelWord(@QueryParam("userid") String userid)  {
        try {
            return controller.getVisabelWord(userid);
        } catch (RemoteException e) {
            e.printStackTrace();
            return "";
        }
    }

    @POST
    @Path("/geatbogstav")
    public boolean gessChar(@QueryParam("letter") String letter, @QueryParam("userid") String userid)  {
        try {
            return controller.gess(letter, userid);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @GET
    @Path("/tjekWin")
    public boolean isTheGameWon(@QueryParam("userid") String userid)  {
        try {
            return controller.tjekWin(userid);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @GET
    @Path("/getAntalForkerteBogstaver")
    @Produces(MediaType.TEXT_PLAIN)
    public int getLife(@QueryParam("userid") String userid)  {
        try {
            return controller.getlife(userid);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @DELETE
    @Path("/destroyGame")
    @Produces(MediaType.APPLICATION_JSON)
    public Response destroyGame(@QueryParam("userid") String userid)  {
        try {
            controller.deleteGame(userid);
            return Response.status(200).build();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return Response.status(204).build();
    }

    @GET
    @Path("/ongoingGame")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean findGame(@QueryParam("userid") String userid)  {
        if (controller.findegame(userid) == null){
            return false;
        }else{
            return true;
        }

    }

}