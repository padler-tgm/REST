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
 * Created by philippadler on 20.12.15.
 */
@Controller
public class DataController {


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

    @RequestMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<DataModel> read(@PathVariable long id){
        DataModel data = dataDao.findOne(id);
        return new ResponseEntity<DataModel>(data, HttpStatus.OK);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseEntity<DataModel> update(@RequestParam(value="id") long id, @RequestParam(value="titel") String titel, @RequestParam(value="beschreibung") String beschreibung){
        DataModel data = dataDao.findOne(id);
        data.setTitel(titel);
        data.setBeschreibung(beschreibung);
        dataDao.save(data);
        return new ResponseEntity<DataModel>(data, HttpStatus.OK);
    }

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

    @RequestMapping(value = "/read")
    public ResponseEntity<List<DataModel>> read(@RequestParam(value="titel") String titel) {
        ArrayList<DataModel> list = new ArrayList<>();
        Iterator<DataModel> data = dataDao.findByTitel(titel).iterator();
        while(data.hasNext()){
            list.add(data.next());
        }
        return new ResponseEntity<List<DataModel>>(list, HttpStatus.OK);
    }

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
