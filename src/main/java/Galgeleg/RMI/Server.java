package Galgeleg.RMI;

import java.rmi.Naming;

public class Server {

    public static String url = "rmi://localhost:1091/Galge";

    public static void main(String[] arg) throws Exception {

        // setup af logik

        IGalgelogik logik = new Galgelogik();
        /*
        try {
            logik.hentOrdFraDr();
            System.out.println("ord hentet!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ord ikke hentet korrekt");
        }
        logik.logStatus();
        */

        // RMI Setup

        // Enten: Kør programmet 'rmiregistry' fra mappen med .class-filerne, eller:
        java.rmi.registry.LocateRegistry.createRegistry(1091); // start i Login.server-JVM

        Naming.rebind(url, logik);
        System.out.println("RMI setup done.");


    }

}
