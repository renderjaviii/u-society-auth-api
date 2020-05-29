package common.authentication.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import common.authentication.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameOrDocumentNumber(String userName, String documentNumber);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrDocumentNumberOrEmailOrPhoneNumber(String username,
                                                                  String documentNumber,
                                                                  String email,
                                                                  String phoneNumber);

}
