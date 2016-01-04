package hello.soapclient;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * Diese Klasse stellt eine Schnittstelle für jede Art von Datenbank-Application dar
 * @author Philipp Adler
 * @version 2014-12-20
 */

public class CLI {

    public static String[][] optionen = {{"h", "true", "hostname"},{"t", "true", "titel"},
            {"p", "true", "port"},{"o", "true", "datei"},};
    private CommandLine cmd;//hier ist gespeichert ob die Option verwendet wurde und wenn die Value der Option
    private Options options;//speichert sich die Optionen die verwendet werden können
    private Map<String, String> arguments;//speichert sich die Value der Optionen
    private Connection con;//speichert Connection Daten

    /**
     * Der Konstruktor nimmt eine Liste von Optionen entgegen die von Application verwaltet werden
     * Diese sind hostname, user, passwort, datenbank, spalte, sortierung, query, trennzeichen, spalten,
     * datei, tabelle.
     */
    public CLI(){
        this.con = null;
        this.options = new Options();//speichert sich alle Optionen in einem Objekt
        System.out.println("Parameter:");
        for(int i=0; i<optionen.length; i++){//gibt alle Optionen aus
            for(int j=0; j<optionen[i].length; j++){
                System.out.print(" -"+optionen[i][j]+" "+optionen[i][j+2]);
                options.addOption(optionen[i][j], Boolean.parseBoolean(optionen[i][++j]), optionen[i][++j]);
            }
            if(i%3 == 0)System.out.println();
        }
    }

    /**
     * Diese Methode fügt eine neue Option hinzu
     * @param opt die Option z.B. h, a, ..
     * @param hasArg ob die Option ein Value hat
     * @param description die Beschreibung der Option
     */
    public void addOption(String opt, boolean hasArg, String description){
        this.options.addOption(opt, hasArg, description);
        System.out.println(" -"+opt+" "+description);
    }

    /**
     * Diese Methode gibt alle Optionen zurück
     * @return alle Optionen die es gibt
     */
    public Options getOptions(){
        return this.options;
    }

    /**
     * In dieser Methode werden die Argumente von CLI übergeben
     * @param args die Argumente die der Benutzer eingegeben hat
     */
    public void addArguments(String[] args){
        CommandLineParser parser = new GnuParser();
        try {
            this.cmd = parser.parse(this.options, args);//hier steht welche Option verwendet und welche Value die Option hat
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.arguments = this.getOptionValue(this.cmd);
    }


    /**
     * Diese Methode benutzt den CommandLineParser um zu kontrollieren welche Optionen der Benutzer gewählt hat
     * @param cmd der CommandLineParser der die verwendeten Optionen und die dazugehörige Value beinhaltet
     * @return die Values um eine Query zu bilden
     */
    public Map<String,String> getOptionValue(CommandLine cmd) {
        Map<String,String> map = new HashMap<>();
        Options optionen = this.getOptions();
        Iterator i = optionen.getOptions().iterator();
        while(i.hasNext()){
            Option o = (Option) i.next();
            map.put(o.getDescription(),"");
        }

        Option[] array = cmd.getOptions();
        for(Option o : array){
            map.replace(o.getDescription(), o.getValue());
        }
        return map;
    }


    /**
     * Diese Methode gibt das Ergebnis der Query entweder als File wenn eins angegeben wurde zurück
     * ansonsten auf der Konsole
     * @param message das Ergebnis der Query welches ausgegeben werden soll
     */
    public void output(SOAPMessage message){
        System.out.print("Response SOAP Message:");
        if(this.arguments.get("datei").equals("")){
            System.out.println();
            System.out.println();
            try {
                message.writeTo(System.out);
            } catch (SOAPException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            File datei = new File(this.arguments.get("datei"));
            try {
                datei.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(datei);
                message.writeTo(fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                System.out.println(this.arguments.get("datei"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (SOAPException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Diese Methode gibt zum angegebenen Key die Value zurück
     * @param key der Key von der die Value gesucht wird
     * @return die Value zum angegegebenen Key
     */
    public String value(String key){
        String value="";
        try{
            if(!this.arguments.get(key).equals("")){
                value = this.arguments.get(key);
            }else{
                System.out.println(key+" wurde nicht angegeben");
                System.exit(0);
            }
        }catch(NullPointerException e){}
        return value;
    }
}