package Controller;

import brugerautorisation.Galgeleg.Galgelogik;

public class Container {

    private String userID;
    private Galgelogik galgelogik;

    public Container(String userID, Galgelogik galgelogik) {
        this.userID = userID;
        this.galgelogik = galgelogik;
    }

    public String getUserID() {
        return userID;
    }

    public Galgelogik getGalgelogik() {
        return galgelogik;
    }

}
