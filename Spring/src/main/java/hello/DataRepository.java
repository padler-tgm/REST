package hello;

import io.spring.guides.gs_producing_web_service.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by philippadler on 28.12.15.
 */

@Component
public class DataRepository {
    private static final List<Data> data = new ArrayList<Data>();

    @PostConstruct
    public void initData() {
        Data spain = new Data();
        spain.setId("22");
        spain.setTitel("x");
        spain.setBeschreibung("y");
        data.add(spain);
    }

    public Data findData(String titel) {
        Assert.notNull(titel);

        Data result = null;

        for (Data country : data) {
            System.out.print(country.getTitel());
            if (titel.equals(country.getTitel())) {
                result = country;
            }
        }

        return result;
    }
}

