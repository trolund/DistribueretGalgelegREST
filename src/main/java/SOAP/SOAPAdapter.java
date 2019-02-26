package SOAP;

import Controller.MainController;

import javax.jws.WebService;
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


}
