package Galgeleg.SOAP;

public class Client {

    /*

    public static void main(String[] args) throws RemoteException {

        ISOAPGelgeleg logik = null;

        System.out.println("Login på serveren: ");

        Scanner input = new Scanner(System.in);
        int count = 0;
        try {
            do {
                if(count > 0){
                    System.out.println("Login oplysninger var ikke korrekte, prøv igen:");
                }
                count++;
            } while (!login());

            logik.hentOrdFraDr();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        }
        System.out.println("EXIT");
    }

    private static boolean login() {
        Scanner input = new Scanner(System.in);

        System.err.print("Brugernavn: ");
        String brugernavn = input.nextLine();
        System.err.print("Adgangskode: ");
        String adgangskode = input.nextLine();

        Brugeradmin ba = null;

        System.out.println("Forsøger at logge ind.....");
        Bruger bruger = null;

        URL url = null;
        URL url2 = null;
        try {
            url = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
            url2 = new URL("http://localhost:9901/brugeradmin?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
        QName qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
            Service service = Service.create(url, qname);
            ba = service.getPort(brugerautorisation.transport.soap.Brugeradmin.class);
            bruger = ba.hentBruger(brugernavn, adgangskode);



        QName qname2 = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
        Service service2 = Service.create(url2, qname);
        logik = service.getPort(SOAPGalgelogik.class);
        bruger = ba.hentBruger(brugernavn, adgangskode);

            System.out.println("velkommen, " + bruger.brugernavn);
            System.out.println("Spillet er startet!");
            return true;
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

    */
}