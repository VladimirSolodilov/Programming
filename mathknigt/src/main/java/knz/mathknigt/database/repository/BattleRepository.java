package knz.mathknigt.database.repository;

import knz.mathknigt.database.model.Battle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleRepository extends CrudRepository<Battle, Long> {
}
