package knz.mathknigt.database.repository;

import knz.mathknigt.database.model.ImpactSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpactSetRepository extends CrudRepository<ImpactSet, Long> {
}
