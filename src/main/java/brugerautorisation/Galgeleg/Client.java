package brugerautorisation.Galgeleg;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws RemoteException {
        IGalgelogik logik = null;
        try {
             logik = (IGalgelogik) Naming.lookup(Server.url);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            System.out.println("Server er ikke startet.....!");
            e.printStackTrace();
        }

        System.out.println("Login på serveren: ");

        Scanner input = new Scanner(System.in);
        int count = 0;

        do {
            if(count > 0){
                System.out.println("Login oplysninger var ikke korrekte, prøv igen:");
            }
            count++;
        } while (!login());

        System.out.println("ordet: " + logik.getSynligtOrd());

        while (!logik.erSpilletSlut()){
            logik.logStatus();
            System.out.println("Gæt på et bogstav:");
            String gæt = input.nextLine();
            char gætChar = '!';

            if(gæt.length() > 0) {
                gætChar = gæt.charAt(0);
            }
            if(gætChar != '!') {
                logik.gætBogstav(gætChar + "");
            } else {
                System.out.println("Noget gik galt...");
                System.out.println("du skal angive et bogstav!");
            }
            
            if(logik.erSidsteBogstavKorrekt()){
                System.out.println("Det er rigtigt!");
            }else {
                System.out.println("Forkert!");
            }
            logStatus(logik);
        }
        System.out.println("EXIT");
    }

    private static boolean login(){
        Scanner input = new Scanner(System.in);

        System.err.print("Brugernavn: "); String brugernavn = input.nextLine();
        System.err.print("Adgangskode: "); String adgangskode = input.nextLine();

        Brugeradmin ba = null;
        try {
            System.out.println("Forsøger at logge ind.....");
            ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
            Bruger bruger = null;
            try {
                bruger = ba.hentBruger(brugernavn, adgangskode);
            }catch (Exception e){
                return false;
            }

            System.out.println("velkommen, " + bruger.brugernavn);
            System.out.println("Spillet er startet!");
            return true;
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
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