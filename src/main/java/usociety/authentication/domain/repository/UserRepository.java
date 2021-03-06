package usociety.authentication.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import usociety.authentication.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);

    List<User> findAllByIdOrUsernameOrEmail(Long id, String username, String email);

    Optional<User> findByUsernameAndAccountLocked(String username, boolean accountLocked);

    Optional<User> findByEmail(String email);

}
