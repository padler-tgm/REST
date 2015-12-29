package hello.rest.Data;

import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

/**
 * Created by philippadler on 20.12.15.
 */
@Transactional
public interface DataDao extends CrudRepository<DataModel, Long>{

    public DataModel findById(long id);
}
