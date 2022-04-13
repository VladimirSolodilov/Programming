package knz.mathknigt.database.repository;

import knz.mathknigt.database.model.Stage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends CrudRepository<Stage, Long> {
}
