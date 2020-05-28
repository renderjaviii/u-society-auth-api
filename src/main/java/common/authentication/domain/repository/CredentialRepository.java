package common.authentication.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import common.authentication.domain.model.Credential;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {

    Optional<Credential> findByClientId(String clientId);

    Optional<Credential> findByUsername(String username);

    Optional<Credential> findByUsernameOrClientId(String username,
                                                  String clientId);

}
