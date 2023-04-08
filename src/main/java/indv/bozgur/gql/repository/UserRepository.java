package indv.bozgur.gql.repository;

import indv.bozgur.gql.model.Role;
import indv.bozgur.gql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    void deleteByRole(Role role);

    Optional<List<User>> findByRole(Role role);

    Optional<User> findByUsername(String username);
}
