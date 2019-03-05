package SOAP;

import Controller.Container;
import Controller.MainController;
import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;

import javax.jws.WebService;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

@WebService(endpointInterface = "SOAP.ISOAPAdapter")
public class SOAPAdapter implements ISOAPAdapter {

    private MainController controller = MainController.getInstance();


    public ArrayList<String> getBrugteBogstaver(String userId){
        return controller.findegame(userId).getGalgelogik().getBrugteBogstaver();
    }

    public String getSynligtOrd(String userId){
        return controller.findegame(userId).getGalgelogik().getSynligtOrd();
    }

    public String getOrdet(String userId){
        return controller.findegame(userId).getGalgelogik().getOrdet();
    }

    public int getAntalForkerteBogstaver(String userId){
        return controller.findegame(userId).getGalgelogik().getAntalForkerteBogstaver();
    }

    public boolean erSidsteBogstavKorrekt(String userId){
        return controller.findegame(userId).getGalgelogik().erSidsteBogstavKorrekt();
    }

    public boolean erSpilletVundet(String userId){
        return controller.findegame(userId).getGalgelogik().erSpilletVundet();
    }

    public boolean erSpilletTabt(String userId){
        return controller.findegame(userId).getGalgelogik().erSpilletTabt();
    }

    public boolean erSpilletSlut(String userId){
        return controller.findegame(userId).getGalgelogik().erSpilletSlut();
    }

    public void nulstil(String userId){
        controller.findegame(userId).getGalgelogik().nulstil();
    }

    public void gætBogstav(String bogstav, String userId){
        controller.findegame(userId).getGalgelogik().gætBogstav(bogstav);
    }

    public void logStatus(String userId){
        controller.findegame(userId).getGalgelogik().logStatus();
    }

    public void hentOrdFraDr(String userId){
        controller.findegame(userId).getGalgelogik().hentOrdFraDr();
    }

    public Bruger login(String userid, String password){
        System.out.println(userid + " forsøger at logge ind via SOAP.....");
        Brugeradmin ba = null;
        try {
            ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Bruger bruger = null;
        try {
            bruger = ba.hentBruger(userid, password);
            System.out.println("bruger: " + userid + " lige logget ind, via SOAP.");
            return bruger;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean findGame(String userid){
        Container container = controller.findegame(userid);
        if(container != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean newGame(String userid) {
        return controller.newGame(userid);
    }

    @Override
    public boolean tjekWin(String userid) throws RemoteException {
        return controller.tjekWin(userid);
    }

    @Override
    public boolean destroyGame(String userid) throws RemoteException {
        return controller.deleteGame(userid);
    }
}
