package SOAP;

import Galgeleg.SOAP.ISOAPGelgeleg;
import Galgeleg.SOAP.SOAPGalgelogik;

import javax.xml.ws.Endpoint;


public class SOAPServer {

    public void startSOAP(){
        // setup af logik

        ISOAPAdapter adapter = new SOAPAdapter();
        // soap Setup

        //Ipv6-addressen [::] svarer til Ipv4-adressen 0.0.0.0, der matcher alle maskinens netkort og IP-adresser
        Endpoint.publish("http://[::]:1791/SOAPService", adapter);
        System.out.println("SOAP setup done.");
    }
}
