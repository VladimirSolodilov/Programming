package knz.myprojectsite.database.repository;

import knz.myprojectsite.database.model.Salt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaltRepository extends CrudRepository<Salt, Long> {
}
