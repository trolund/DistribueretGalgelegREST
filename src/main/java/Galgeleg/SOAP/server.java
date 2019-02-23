package Galgeleg.SOAP;

import javax.xml.ws.Endpoint;

public class server {

    public static void main(String[] arg) throws Exception {

        // setup af logik

        ISOAPGelgeleg logik = new SOAPGalgelogik();

        // soap Setup

        //Ipv6-addressen [::] svarer til Ipv4-adressen 0.0.0.0, der matcher alle maskinens netkort og IP-adresser
         Endpoint.publish("http://[::]:9922/kontotjeneste", logik);
         System.out.println("SOAP setup done.");

    }
}
