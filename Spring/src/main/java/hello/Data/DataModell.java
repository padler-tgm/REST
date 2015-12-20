package hello.Data;

import javax.persistence.*;

/**
 * Created by philippadler on 20.12.15.
 */

@Entity
@Table(name = "data")
public class DataModell {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "titel")
    private String titel;

    @Column(name = "beschreibung")
    private String beschreibung;

    public DataModell(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
