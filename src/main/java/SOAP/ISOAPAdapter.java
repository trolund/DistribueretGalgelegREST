package SOAP;

import Controller.MainController;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public interface ISOAPAdapter {

    MainController controller = MainController.getInstance();

    String userId = "";

    @WebMethod void setUserId(String userId);

    @WebMethod ArrayList<String> getBrugteBogstaver();

    @WebMethod String getSynligtOrd();

    @WebMethod String getOrdet();

    @WebMethod int getAntalForkerteBogstaver();

    @WebMethod boolean erSidsteBogstavKorrekt();

    @WebMethod boolean erSpilletVundet();

    @WebMethod boolean erSpilletTabt();

    @WebMethod boolean erSpilletSlut();

    @WebMethod void nulstil();

    @WebMethod void gætBogstav(String bogstav);

    @WebMethod void logStatus();

    @WebMethod void hentOrdFraDr();

}
