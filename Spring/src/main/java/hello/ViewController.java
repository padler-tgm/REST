package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by philippadler on 27.12.15.
 */
@Controller
public class ViewController {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody
    public String index() {
        return "Willkommen bei AK-Wiki </br>" +
                "<a href='./create?'>CREATE</a>" + "</br>" +
                "<a href='./update?'>UPDATE</a>" + "</br>" +
                "<a href='./delete?'>DELETE</a>";
    }
}
