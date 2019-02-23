package Controller;

import Galgeleg.RMI.Galgelogik;
import Galgeleg.RMI.IGalgelogik;
import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MainController {

    // static variable single_instance of type Singleton
    private static MainController single_instance = null;

    // variable of type String
    public String s;

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

    private IGalgelogik galgelogik;

    {
        try {
            galgelogik = new Galgelogik();
            galgelogik.hentOrdFraDr();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean gess(String c) throws RemoteException {
        galgelogik.gætBogstav(c);
        galgelogik.logStatus();
        return galgelogik.erSidsteBogstavKorrekt();
    }

    // RMI til jakobs server.
    public boolean login(String brugernavn, String adgangskode) {
        Brugeradmin ba = null;

        System.out.println("Forsøger at logge ind.....");
        Bruger bruger = null;
        try {
            ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
            bruger = ba.hentBruger(brugernavn, adgangskode);
            System.out.println("velkommen, " + bruger.brugernavn);
            System.out.println("Ordet: " + galgelogik.getOrdet());
            return true;
        } catch (NotBoundException e1) {
            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    public String getVisabelWord() throws RemoteException {
          return galgelogik.getSynligtOrd();
    }

    public int getlife() throws RemoteException {
        return galgelogik.getAntalForkerteBogstaver();
    }

    public static void logStatus(IGalgelogik logik) throws RemoteException {
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
