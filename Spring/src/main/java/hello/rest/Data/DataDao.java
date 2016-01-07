package hello.rest.Data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Created by philippadler on 20.12.15.
 */
@Transactional
public interface DataDao extends CrudRepository<DataModel, Long>{
    public Iterable<DataModel> findByTitel(String titel);
    public Iterable<DataModel> findAll();
}
