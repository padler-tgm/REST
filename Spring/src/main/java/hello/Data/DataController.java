package hello.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by philippadler on 20.12.15.
 */
@Controller
public class DataController {


    @RequestMapping("/create")
    @ResponseBody
    public String create(@RequestParam(value="titel") String titel, @RequestParam(value="beschreibung") String beschreibung){
        DataModel data = null;
        try{
            data = new DataModel(titel, beschreibung);
            dataDao.save(data);
        }catch (Exception e){
            return "Error creating the data: "+ e.toString();
        }
        return "Data succesfully created with id = " + data.getId();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam(value="id") long id, @RequestParam(value="titel") String titel, @RequestParam(value="beschreibung") String beschreibung){
        try{
            DataModel data = dataDao.findOne(id);
            data.setTitel(titel);
            data.setBeschreibung(beschreibung);
            dataDao.save(data);
        }catch (Exception e){
            return "Error updating the data: "+e.toString();
        }
        return "Data succesfully updated!";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(value="id") long id) {
        try{
            DataModel data = new DataModel(id);
            dataDao.delete(data);
        }catch (Exception e){
            return "Error deleting the data: "+e.toString();
        }
        return "Data succesfully deleted!";
    }

    @Autowired
    private DataDao dataDao;
}
