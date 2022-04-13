package knz.mathknigt.database.repository;

import knz.mathknigt.database.model.Personality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalityRepository extends CrudRepository<Personality, Long> {
}
