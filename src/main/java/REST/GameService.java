package REST;

import Controller.MainController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.rmi.RemoteException;

@Path("/game")
public class GameService {

    private MainController controller = MainController.getInstance();

    @GET
    public String getDiscription(){
        return "<html>" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "<script>\n" +
                "var val;" +
                "function myFunction() {\n" +
                "val = document.getElementById(\"input\").value;\n" +
                "var a = document.getElementsByTagName('a'),\n" +
                "    length = a.length;" +
                "\n" +
                "for(var i=0; i< length; i++){\n" +
                "    a[i].href += '?userid=' + val;\n" +
                "}" +
                "}" +
                "function myGess() {\n" +
                "    var gessVal = document.getElementById(\"gessVal\").value;\n" +
                "    document.getElementById('gess').href = 'game/geatbogstav?userid=' + val + '?letter=' + gessVal;\n" +
                "DoPost(gessVal);" +
                "}" +
                "function DoPost(gess){\n" +
                "      $.post(\"game/getAntalForkerteBogstaver\", { userid: \"val\", letter: \"gess\" } );  //Your values here..\n" +
                "   }" +
                "</script>" +
                "</head><body>" +
                "<p>Du bliver nød til at specificere hvilke resurse du ønsker.<br> \n <h3>Hvad er dit brugernavn?</h3><br>\n" +
                "  <form>\n" +
                "    <input id=\"input\" type=\"text\" name=\"brugernavn\">\n" +
                "  </form>" +
                "<button onclick=\"myFunction()\">Angiv brugernavn</button>\n" +
                "<br>Kunne det være:</p>\n" +
                "<p>For at få hele ordet ord: GET </p><a href=\"game/word\">game/word</a>" +
                "<p>Forlad spillet: GET </p><a href=\"game/gameExist\">game/gameExist</a>" +
                "<p>Se vinder log: GET </p><a href=\"game/getScoreboard\">game/getScoreboard</a>" +
                "<p>Se brugte bogstaver for spil: GET </p><a href=\"game/usedLetters\">game/usedLetters</a>" +
                "<p>Se om spillet er igang: GET </p><a href=\"game/ongoingGame\">game/ongoingGame</a>" +
                "<p>Slet spil: DELETE </p><a href=\"game/destroyGame\">game/destroyGame</a>" +
                "<p>Tjek om spillet er vundet: GET </p><a href=\"game/tjekWin\">game/tjekWin</a>" +
                "<p>Gennem tving sletning af spil: POST </p><a href=\"game/forceDeleteGame\">game/forceDeleteGame</a>" +
                "<p>Gennem tving nyt spil: POST </p><a href=\"game/forceNewGame\">game/forceNewGame</a>" +
                "<p>Nyt spil: POST </p><a href=\"game/newGame\">game/newGame</a>" +
                "<p>For at få syndligt ord: GET </p><a href=\"game/synligtord\">game/synligtord</a>" +
                "<p>For at gætte er bogstav: POST </p><a id=\"gess\" href=\"game/geatbogstav\">game/geatbogstav</a>" +
                "<input id=\"gessVal\" type=\"text\" name=\"brugernavn\">\n" +
                "<button onclick=\"myGess()\">Angiv bogstav</button>\n" +
                "<p>AntalForkerteBogstaver : GET </p><a href=\"game/getAntalForkerteBogstaver\">game/getAntalForkerteBogstaver</a>"+
                "</body></html>";
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
    @Produces(MediaType.TEXT_PLAIN)
    public String destroyGame(@QueryParam("userid") String userid)  {
        try {
            controller.deleteGame(userid);
            System.out.println("spil ");
            return "spillet er blevet slettet.";
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "spillet er blev IKKE slettet.";
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

    @GET
    @Path("/usedLetters")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usedLetters(@QueryParam("userid") String userid)  {
        return Response.status(200).entity(controller.usedChar(userid)).build();
    }

    @GET
    @Path("/getScoreboard")
    @Produces(MediaType.APPLICATION_JSON)
    public String getScoreboardAsJson()  {
        try {
            return controller.getScoreboardAsJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/gameExist")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean gameExist(@QueryParam("userid") String userid)  {
        return controller.gameExist(userid);
    }

    @GET
    @Path("/word")
    @Produces(MediaType.TEXT_PLAIN)
    public String word(@QueryParam("userid") String userid)  {
        return controller.word(userid);
    }

}