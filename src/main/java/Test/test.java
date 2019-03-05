package Test;

import Controller.MainController;
import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class test {

    public static void main(String[] args) {
        MainController controller = MainController.getInstance();

        try {
            controller.login("s161791", "kode123");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
