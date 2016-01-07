package hello.soa;

import hello.rest.Data.DataDao;
import hello.rest.Data.DataModel;
import io.spring.guides.gs_producing_web_service.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by philippadler on 28.12.15.
 */

@Component
public class DataRepository {
    //private static final List<Data> data = new ArrayList<Data>();
    @Autowired
    private DataDao dataDao;

    @PostConstruct
    public void initData() {
        /**Data spain = new Data();
        spain.setId("22");
        spain.setTitel("x");
        spain.setBeschreibung("y");
        data.add(spain);**/
    }

    public List<Data> findData(String titel) {
        Assert.notNull(titel);

        List<Data> result = new ArrayList<>();

        /**for (Data country : data) {
            System.out.print(country.getTitel());
            if (titel.equals(country.getTitel())) {
                result = country;
            }
        }**/
        Iterator<DataModel> data = dataDao.findByTitel(titel).iterator();
        while(data.hasNext()){
            DataModel model = data.next();
            Data element = new Data();
            element.setId(model.getId()+"");
            element.setTitel(model.getTitel());
            element.setBeschreibung(model.getBeschreibung());
            result.add(element);
        }

        return result;
    }
}

