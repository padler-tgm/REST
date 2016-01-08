package hello.rest.Data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Interface fuer das Verwalten des DataModels
 * @author Philipp Adler
 * @version 20.12.15
 */
@Transactional
public interface DataDao extends CrudRepository<DataModel, Long>{
    /**
     * Liefert alle Objekte deren Titel gleich des Parameters sind
     * @param titel der gesuchte Titel
     * @return Liste von Objekten die den Titel erhalten
     */
    public Iterable<DataModel> findByTitel(String titel);

    /**
     * Liefert alle Objekte zurueck
     * @return alle Objekte in der Datenbank
     */
    public Iterable<DataModel> findAll();
}
