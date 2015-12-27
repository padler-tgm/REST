package hello.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by philippadler on 20.12.15.
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

    public DataModel(){}

    public DataModel(long id){
        this.id = id;
    }

    public DataModel(String titel, String beschreibung){
        this.titel = titel;
        this.beschreibung = beschreibung;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
