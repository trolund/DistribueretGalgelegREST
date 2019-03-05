package Controller;

import Galgeleg.RMI.IGalgelogik;
import SOAP.SOAPServer;
import SaveScoreboardFile.SaveAndLoadScoreboard;
import SaveScoreboardFile.SaveContainer;
import brugerautorisation.Galgeleg.Galgelogik;
import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import com.google.gson.Gson;

import javax.validation.constraints.Null;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class MainController {

    // static variable single_instance of type Singleton
    private static MainController single_instance = null;

    private static ArrayList<Container> gamelist = new ArrayList<>();
   // private ArrayList<SaveContainer> scroelist = new ArrayList<>();

    static
    {
        SOAPServer soapServer = new SOAPServer();
        soapServer.startSOAP();
    }

    // private constructor restricted to this class itself
    private MainController()
    {
    }

    // static method to create instance of Singleton class
    public static MainController getInstance()
    {
        if (single_instance == null)
            single_instance = new MainController();

        return single_instance;
    }

    public boolean newGame(String userid){
        Container container =  findegame(userid);

        if(container == null){  // hvis der ikke findes et spil lav et nyt
            try {
                brugerautorisation.Galgeleg.Galgelogik logik = new brugerautorisation.Galgeleg.Galgelogik();
                logik.hentOrdFraDr();
                gamelist.add(new Container(userid, logik));
                logStatus(userid);
                return true; // new game statet.
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        logStatus(userid);
        return false;  // resume game
    }

    public String getScoreboardAsJson() throws IOException {
        SaveAndLoadScoreboard file = new SaveAndLoadScoreboard();
        Gson gson = new Gson();
        ArrayList<SaveContainer> list = file.loadList();
        return gson.toJson(list);
    }

    public void forceNewGame(String userid) throws RemoteException {
        Container container =  findegame(userid);  // find gamelt spil
        gamelist.remove(container);  // selt det gamle spil.
        gamelist.add(new Container(userid, new Galgelogik())); // opret nyt spil.
        logStatus(userid);
    }

    public boolean tjekWin(String userid) throws RemoteException {
        Container container =  findegame(userid);  // find gamelt spil
        boolean winBool = container.getGalgelogik().erSpilletVundet();

        if(winBool) {
            SaveContainer saveContainer = new SaveContainer();
            SaveAndLoadScoreboard file = new SaveAndLoadScoreboard();

            saveContainer.setWord(container.getGalgelogik().getOrdet());
            saveContainer.setUsedLetters(container.getGalgelogik().getBrugteBogstaver());
            saveContainer.setUserid(container.getUserID());
            saveContainer.setTimeStamp(new Timestamp(System.currentTimeMillis()));

            try {
                file.saveEntriy(saveContainer);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return winBool;
    }

    public boolean deleteGame(String userid) throws RemoteException {
        Container container =  findegame(userid);  // find gamelt spil
        if(container != null) {
            gamelist.remove(container);  // selt det gamle spil.
            //logStatus(userid);
            return true;
        }
        return false;
    }

    public Container findegame(String userid){  // findes der et spil som er igang?
        //System.out.println(gamelist);
        for (Container item:  gamelist) {
            if (item.getUserID().equals(userid)){
                return item;
            }
        }
        return null;
    }

    public ArrayList<String> usedChar(String userid){
        Container container = findegame(userid);
        return container.getGalgelogik().getBrugteBogstaver();
    }

    public boolean gess(String c, String userid) throws RemoteException {
        Container game = findegame(userid);
        game.getGalgelogik().gætBogstav(c);
        logStatus(userid);
        return game.getGalgelogik().erSidsteBogstavKorrekt();
    }

    private void logStatus(String userid) {
        Galgelogik galgelogik = findegame(userid).getGalgelogik();
        System.out.println("Number of games: " + gamelist.size());
        System.out.println("---------- ");
        System.out.println("- synligtOrd = " + galgelogik.getSynligtOrd());
        System.out.println("ord: ----> " + galgelogik.getOrdet());
        System.out.println("- brugeBogstaver = " + galgelogik.getBrugteBogstaver());
        int antalLiv = 7-galgelogik.getAntalForkerteBogstaver();
        System.out.println("Liv tilbage: " + antalLiv);
        if (galgelogik.erSpilletTabt()) System.out.println("- SPILLET ER TABT");
        if (galgelogik.erSpilletVundet()) System.out.println("- SPILLET ER VUNDET");
        asciiHangman(antalLiv);
        System.out.println("---------- ");
    }

    // RMI til jakobs server.
    public boolean login(String brugernavn, String adgangskode) throws RemoteException, NotBoundException, MalformedURLException {
        Brugeradmin ba = null;

        System.out.println("Forsøger at logge ind.....");
        Bruger bruger = null;
            ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
            bruger = ba.hentBruger(brugernavn, adgangskode);
            if(bruger != null) {
                System.out.println("velkommen, " + bruger.brugernavn);
                return true;
            }else {
                return false;
            }
    }

    public String getVisabelWord(String userid) throws RemoteException {
        Container game = findegame(userid);
          return game.getGalgelogik().getSynligtOrd();
    }

    public int getlife(String userid) throws RemoteException {
        Container game = findegame(userid);
        return game.getGalgelogik().getAntalForkerteBogstaver();
    }
/*
    public static void logStatus(Galgelogik logik) throws RemoteException {
        System.out.println("---------- ");
        System.out.println("- synligtOrd = " + logik.getSynligtOrd());
        System.out.println("- brugeBogstaver = " + logik.getBrugteBogstaver());
        int antalLiv = 7-logik.getAntalForkerteBogstaver();
        System.out.println("Liv tilbage: " + antalLiv);
        if (logik.erSpilletTabt()) System.out.println("- SPILLET ER TABT");
        if (logik.erSpilletVundet()) System.out.println("- SPILLET ER VUNDET");
        asciiHangman(antalLiv);
        System.out.println("---------- ");
    }
*/
    private static void asciiHangman(int life){
        String art ="";
        switch (life){
            case 0:
                art = "    _______\n" +
                        "     |/      |\n" +
                        "     |      (_)\n" +
                        "     |      \\|/\n" +
                        "     |       |\n" +
                        "     |      / \\\n" +
                        "     |\n" +
                        " ____|____";
                break;
            case 1:
                art = "    _______\n" +
                        "     |/      |\n" +
                        "     |      (_)\n" +
                        "     |      \\|/\n" +
                        "     |       |\n" +
                        "     |      / \n" +
                        "     |\n" +
                        " ____|____";
                break;
            case 2:
                art = "    _______\n" +
                        "     |/      |\n" +
                        "     |      (_)\n" +
                        "     |      \\|/\n" +
                        "     |       |\n" +
                        "     |       \n" +
                        "     |\n" +
                        " ____|____";
                break;
            case 3:
                art = "    _______\n" +
                        "     |/      |\n" +
                        "     |      (_)\n" +
                        "     |      \\|/\n" +
                        "     |       \n" +
                        "     |       \n" +
                        "     |\n" +
                        " ____|____";
                break;
            case 4:
                art = "    _______\n" +
                        "     |/      |\n" +
                        "     |      (_)\n" +
                        "     |      \\|\n" +
                        "     |       \n" +
                        "     |       \n" +
                        "     |\n" +
                        " ____|____";
                break;
            case 5:
                art = "    _______\n" +
                        "     |/      |\n" +
                        "     |      (_)\n" +
                        "     |       |\n" +
                        "     |       \n" +
                        "     |       \n" +
                        "     |\n" +
                        " ____|____";
                break;
            case 6:
                art = "    _______\n" +
                        "     |/      |\n" +
                        "     |      (_)\n" +
                        "     |       \n" +
                        "     |       \n" +
                        "     |       \n" +
                        "     |\n" +
                        " ____|____";
                break;
            case 7:
                art = "    _______\n" +
                        "     |/      |\n" +
                        "     |      \n" +
                        "     |       \n" +
                        "     |       \n" +
                        "     |       \n" +
                        "     |\n" +
                        " ____|____";
                break;
            default:
                art = "    _______\n" +
                        "     |/      |\n" +
                        "     |      \n" +
                        "     |       \n" +
                        "     |       \n" +
                        "     |       \n" +
                        "     |\n" +
                        " ____|____";
                break;
        }
        System.out.println(art);

    }



}
