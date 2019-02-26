package SOAP;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class clientTest {

        public static void main(String[] args) throws MalformedURLException {
            //URL url = new URL("http://130.225.170.204:9901/kontotjeneste?wsdl");
            URL url = new URL("http://localhost:9922/SOAPService?wsdl");
            QName qname = new QName("http://SOAP/", "SOAPAdapterService");
            Service service = Service.create(url, qname);
            ISOAPAdapter adapter = service.getPort(ISOAPAdapter.class);

            System.out.println(adapter.erSpilletVundet("s161791"));
        }
    }

