package knz.mathknigt.database.repository;

import knz.mathknigt.database.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM user WHERE email = :email limit 1;", nativeQuery = true)
    User findUserByEmail(@Param("email") @NonNull String email);
}
