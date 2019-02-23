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

        controller.login("s161791", "kode123");
    }
}
