package SOAP;

import brugerautorisation.data.Bruger;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.rmi.RemoteException;
import java.util.ArrayList;

@WebService
public interface ISOAPAdapter {

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

    @WebMethod Bruger login(String userid, String password);

    @WebMethod boolean findGame(String userid);

    @WebMethod boolean newGame(String userid);

    @WebMethod boolean tjekWin(String userid) throws RemoteException;

    @WebMethod boolean destroyGame(String userid) throws RemoteException;

}
