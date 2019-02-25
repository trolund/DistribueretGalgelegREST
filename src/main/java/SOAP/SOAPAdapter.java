package SOAP;

import Controller.MainController;

import javax.jws.WebService;
import java.util.ArrayList;

@WebService(endpointInterface = "SOAP.ISOAPAdapter")
public class SOAPAdapter {

    private MainController controller = MainController.getInstance();

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<String> getBrugteBogstaver(){
        return controller.findegame(userId).getGalgelogik().getBrugteBogstaver();
    }

    public String getSynligtOrd(){
        return controller.findegame(userId).getGalgelogik().getSynligtOrd();
    }

    public String getOrdet(){
        return controller.findegame(userId).getGalgelogik().getOrdet();
    }

    public int getAntalForkerteBogstaver(){
        return controller.findegame(userId).getGalgelogik().getAntalForkerteBogstaver();
    }

    public boolean erSidsteBogstavKorrekt(){
        return controller.findegame(userId).getGalgelogik().erSidsteBogstavKorrekt();
    }

    public boolean erSpilletVundet(){
        return controller.findegame(userId).getGalgelogik().erSpilletVundet();
    }

    public boolean erSpilletTabt(){
        return controller.findegame(userId).getGalgelogik().erSpilletTabt();
    }

    public boolean erSpilletSlut(){
        return controller.findegame(userId).getGalgelogik().erSpilletSlut();
    }

    public void nulstil(){
        controller.findegame(userId).getGalgelogik().nulstil();
    }

    public void gætBogstav(String bogstav){
        controller.findegame(userId).getGalgelogik().gætBogstav(bogstav);
    }

    public void logStatus(){
        controller.findegame(userId).getGalgelogik().logStatus();
    }

    public void hentOrdFraDr(){
        controller.findegame(userId).getGalgelogik().hentOrdFraDr();
    }


}
