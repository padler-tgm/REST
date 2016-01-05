package hello.rest.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by philippadler on 27.12.15.
 */
@Controller
public class ViewController {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public String index(Model model) {
        return "index";
    }


    @RequestMapping(value = "/read", method = RequestMethod.GET, produces = "text/html")
    public String displayDataRecord(@RequestParam(value="titel") String titel, @RequestParam(value="site") int site, Model model) {
        Integer anzahl = 10;
        List<DataModel> data = dataController.read(titel).getBody();
        List<DataModel> data1 = null;
        int bis = 0;
        if(data.size() > 0) {
            if(anzahl * site < data.size()){
                bis = anzahl * site;
            }else{
                bis = data.size();
            }
            data1 = new ArrayList<DataModel>(data.subList(anzahl * (site - 1), bis));
        }
        model.addAttribute("titel", titel);
        Double pagination = (Math.ceil(data.size()/anzahl.doubleValue()));
        data = new ArrayList<DataModel>(data.subList(0, pagination.intValue()));
        model.addAttribute("data", data);
        model.addAttribute("data1", data1);
        model.addAttribute("site", site);
        model.addAttribute("pagination", pagination);

        // IntelliJ bug workaround (see class comment)
        if (false) {
            WebContext context = new org.thymeleaf.context.WebContext(null, null, null);
            context.setVariable("titel", titel);
            context.setVariable("data", data);
            context.setVariable("data1", data1);
            context.setVariable("site", site);
            context.setVariable("pagination", pagination);
        }

        return "read";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET, produces = "text/html")
    public String readDataRecord(@PathVariable long id, Model model) {
        DataModel data = dataController.read(id).getBody();
        model.addAttribute("data", data);

        // IntelliJ bug workaround (see class comment)
        if (false) {
            WebContext context = new org.thymeleaf.context.WebContext(null, null, null);
            context.setVariable("data", data);
        }
        return "update";
    }

    @RequestMapping(value = "/update/", method = RequestMethod.GET, produces = "text/html")
    public String updateDataRecord(@RequestParam(value="id") long id, @RequestParam(value="titel") String titel, @RequestParam(value="beschreibung") String beschreibung, Model model) {
        DataModel data = dataController.update(id, titel, beschreibung).getBody();
        model.addAttribute("data", data);

        // IntelliJ bug workaround (see class comment)
        if (false) {
            WebContext context = new org.thymeleaf.context.WebContext(null, null, null);
            context.setVariable("data", data);
        }
        return "index";
    }

    @RequestMapping(value = "/create/", method = RequestMethod.GET, produces = "text/html")
    public String createDataRecord(@RequestParam(value="titel", defaultValue = "") String titel, @RequestParam(value="beschreibung", defaultValue = "") String beschreibung, Model model) {
        if(!(titel.equals("") || beschreibung.equals(""))) {
            dataController.create(titel, beschreibung);
            return "index";
        }else{
            return "create";
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "text/html")
    public String deleteDataRecord(@PathVariable long id, Model model) {
        dataController.delete(id);
        return "index";
    }


    @Autowired
    private DataController dataController;
}
