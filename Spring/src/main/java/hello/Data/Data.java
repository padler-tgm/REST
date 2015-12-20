package hello.Data;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by philippadler on 20.12.15.
 */
public interface Data extends CrudRepository<DataModell, Integer>{
}
