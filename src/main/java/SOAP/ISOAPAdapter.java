package SOAP;

import Controller.MainController;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public interface ISOAPAdapter {

    MainController controller = MainController.getInstance();

    @WebMethod ArrayList<String> getBrugteBogstaver(String userId);

    @WebMethod String getSynligtOrd(String userId);

    @WebMethod String getOrdet(String userId);

    @WebMethod int getAntalForkerteBogstaver(String userId);

    @WebMethod boolean erSidsteBogstavKorrekt(String userId);

    @WebMethod boolean erSpilletVundet(String userId);

    @WebMethod boolean erSpilletTabt(String userId);

    @WebMethod boolean erSpilletSlut(String userId);

    @WebMethod void nulstil(String userId);

    @WebMethod void gætBogstav(String bogstav, String userId);

    @WebMethod void logStatus(String userId);

    @WebMethod void hentOrdFraDr(String userId);

}
