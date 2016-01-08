package hello.rest.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Model fuer die Tabelle in der Datenbank
 * @author Philipp Adler
 * @version 20.12.15
 */

@Entity
@Table(name = "data")
public class DataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String titel;

    @NotNull
    private String beschreibung;

    /**
     * Default-Konstruktor
     */
    public DataModel(){}

    /**
     * Konstruktor
     * @param id id des Objekts
     */
    public DataModel(long id){
        this.id = id;
    }

    /**
     * Konstruktor
     * @param titel Titel
     * @param beschreibung Beschreibung
     */
    public DataModel(String titel, String beschreibung){
        this.titel = titel;
        this.beschreibung = beschreibung;
    }

    /**
     * Liefert die Id
     * @return Id
     */
    public long getId() {
        return id;
    }

    /**
     * Aendert die Id
     * @param id die neue Id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Liefert den Titel
     * @return Titel
     */
    public String getTitel() {
        return titel;
    }

    /**
     * Aendert den Titel
     * @param titel der neue Titel
     */
    public void setTitel(String titel) {
        this.titel = titel;
    }

    /**
     * Liefert die Beschreibung
     * @return Beschreibung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Aendert die Beschreibung
     * @param beschreibung die neue Beschreibung
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }


}
