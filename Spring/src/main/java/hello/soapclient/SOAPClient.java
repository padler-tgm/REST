package hello.soapclient;

import javax.xml.soap.*;


/**
 * http://stackoverflow.com/questions/15948927/working-soap-client-example
 */

/**
 * SOAP-Client mit Commandline UI
 * @author Adin Karic
 * @version 20-01-04
 */
public class SOAPClient {
    /**
     * main nimmt die Usereingabe entgegen
     * @param args Hostname, Port, Titel, Dateiname
     * @throws Exception falls keine Verbindung aufgebaut werden konnte
     */
    public static void main(String args[]) throws Exception {
        CLI cli = new CLI();
        cli.addArguments(args);
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        try {
            String url = "http://" + cli.value("hostname") + ":" + cli.value("port") + "/ws";
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(cli.value("titel")), url);

            // print SOAP Response
            cli.output(soapResponse);
        }catch (Exception e){

        }

        soapConnection.close();
    }

    /**
     * Erzeugt einen Request
     * @param titel der Titel im Request nach dem dann gesucht wird
     * @return den Request
     * @throws Exception
     */
    private static SOAPMessage createSOAPRequest(String titel) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://spring.io/guides/gs-producing-web-service";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("gs", serverURI);

        /*
        Constructed SOAP Request Message:
        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:example="http://ws.cdyne.com/">
            <SOAP-ENV:Header/>
            <SOAP-ENV:Body>
                <example:VerifyEmail>
                    <example:email>mutantninja@gmail.com</example:email>
                    <example:LicenseKey>123</example:LicenseKey>
                </example:VerifyEmail>
            </SOAP-ENV:Body>
        </SOAP-ENV:Envelope>
         */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("getDataRequest", "gs");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("titel", "gs");
        soapBodyElem1.addTextNode(titel);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "getDataRequest");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

}