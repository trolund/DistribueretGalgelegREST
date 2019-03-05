package SOAP;

import brugerautorisation.data.Bruger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Scanner;

public class SOAPClient {

    private static Bruger bruger = null;

    public static void main(String[] args) {
        ISOAPAdapter logik = null;

        URL url = null;
        try {
            url = new URL("http://localhost:1791/SOAPService?wsdl");
           // url = new URL("https://distgalgeleg.herokuapp.com:1791/SOAPService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        QName qname = new QName("http://SOAP/", "SOAPAdapterService");
        Service service = Service.create(url, qname);
        logik = service.getPort(ISOAPAdapter.class);

        System.out.println("Login på serveren: ");

        Scanner input = new Scanner(System.in);
        int count = 0;
        try {
            do {
                if(count > 0){
                    System.out.println("Login oplysninger var ikke korrekte, prøv igen:");
                }
                count++;

                login(logik);

            } while (bruger == null);

            if(!logik.findGame(bruger.brugernavn)) { // opret et spil hvis der ikke findes et i forvejen.
                logik.newGame(bruger.brugernavn);
                logik.hentOrdFraDr(bruger.brugernavn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ordet: " + logik.getSynligtOrd(bruger.brugernavn));

        while (!logik.erSpilletSlut(bruger.brugernavn)){
            logik.logStatus(bruger.brugernavn);
            System.out.println("Gæt på et bogstav:");
            String gæt = input.nextLine();
            char gætChar = '!';

            if(gæt.length() > 0) {
                gætChar = gæt.charAt(0);
            }
            if(gætChar != '!') {
                logik.gætBogstav(gætChar + "", bruger.brugernavn);
            } else {
                System.out.println("Noget gik galt...");
                System.out.println("du skal angive et bogstav!");
            }

            if(logik.erSidsteBogstavKorrekt(bruger.brugernavn)){
                System.out.println("Det er rigtigt!");
            }else {
                System.out.println("Forkert!");
            }
            logStatus(logik);
        }

        try {
            logik.tjekWin(bruger.brugernavn);
            logik.destroyGame(bruger.brugernavn);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        System.out.println("EXIT");
    }

    private static void login(ISOAPAdapter logik) {
        Scanner input = new Scanner(System.in);

        System.err.print("Brugernavn: ");
        String brugernavn = input.nextLine();
        System.err.print("Adgangskode: ");
        String adgangskode = input.nextLine();

        bruger = logik.login(brugernavn, adgangskode);
        }

    public static void logStatus(ISOAPAdapter logik) {
        System.out.println("---------- ");
        System.out.println("- synligtOrd = " + logik.getSynligtOrd(bruger.brugernavn));
        System.out.println("- brugeBogstaver = " + logik.getBrugteBogstaver(bruger.brugernavn));
        int antalLiv = 7-logik.getAntalForkerteBogstaver(bruger.brugernavn);
        System.out.println("Liv tilbage: " + antalLiv);
        if (logik.erSpilletTabt(bruger.brugernavn)) System.out.println("- SPILLET ER TABT");
        if (logik.erSpilletVundet(bruger.brugernavn)) System.out.println("- SPILLET ER VUNDET");
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