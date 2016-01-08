package hello.rest.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Controller der im Hintergrund fungiert
 * @author Philipp Adler
 * @version 20.12.15
 */
@Controller
public class DataController {

    /**
     * Create-Methode nimmt Titel und Beschreibung entgegen
     * @param titel Titel des DataModells
     * @param beschreibung Beschreibung des DataModells
     * @return ob successful oder error
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public String create(@RequestParam(value="titel", defaultValue = "") String titel, @RequestParam(value="beschreibung", defaultValue = "") String beschreibung){
        DataModel data = null;
        if(!(titel.equals("") || beschreibung.equals(""))) {
            try {
                data = new DataModel(titel, beschreibung);
                dataDao.save(data);
            } catch (Exception e) {
                return "Error creating the data: " + e.toString();
            }
            return "Data succesfully created with id = " + data.getId();
        }else{
            return null;
        }
    }

    /**
     * Read-Methode liefert DataModell anhand der Id zurueck
     * @param id die Id des DataModells
     * @return das DataModell mit der uebergebenen Id
     */
    @RequestMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<DataModel> read(@PathVariable long id){
        DataModel data = dataDao.findOne(id);
        return new ResponseEntity<DataModel>(data, HttpStatus.OK);
    }

    /**
     * Update-Methode uebernimmt die neuen Eigenschaften
     * @param id die Id es DataModells
     * @param titel neuer Titel
     * @param beschreibung neue Beschreibung
     * @return das geaenderte DataModell
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResponseEntity<DataModel> update(@RequestParam(value="id") long id, @RequestParam(value="titel") String titel, @RequestParam(value="beschreibung") String beschreibung){
        DataModel data = dataDao.findOne(id);
        data.setTitel(titel);
        data.setBeschreibung(beschreibung);
        dataDao.save(data);
        return new ResponseEntity<DataModel>(data, HttpStatus.OK);
    }

    /**
     * Delete-Methode loescht das DataModell mit der uebergebenen Id
     * @param id das Objekt welches geloescht werden soll
     * @return ob successful oder error
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@PathVariable long id) {
        try{
            DataModel data = new DataModel(id);
            dataDao.delete(data);
        }catch (Exception e){
            return "Error deleting the data: "+e.toString();
        }
        return "Data succesfully deleted!";
    }

    /**
     * Read-Methode fuer alle Elemente die den uebergebenen Titel beinhalten
     * @param titel der gesuchte Titel
     * @return die gefundenen Ergebnisse
     */
    @RequestMapping(value = "/read")
    public ResponseEntity<List<DataModel>> read(@RequestParam(value="titel") String titel) {
        ArrayList<DataModel> list = new ArrayList<>();
        Iterator<DataModel> data = dataDao.findByTitel(titel).iterator();
        while(data.hasNext()){
            list.add(data.next());
        }
        return new ResponseEntity<List<DataModel>>(list, HttpStatus.OK);
    }

    /**
     * Liefert alle Objekte
     * @return alle Objekte
     */
    @RequestMapping(value = "/")
    public ResponseEntity<List<DataModel>> readall() {
        ArrayList<DataModel> list = new ArrayList<>();
        Iterator<DataModel> data = dataDao.findAll().iterator();
        while(data.hasNext()){
            list.add(data.next());
        }
        return new ResponseEntity<List<DataModel>>(list, HttpStatus.OK);
    }

    @Autowired
    private DataDao dataDao;
}
