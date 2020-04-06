package fr.adservio.crm.utilisateurs.api.repositories;

import fr.adservio.crm.utilisateurs.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * CRUD interface for User entity
 * @author adservio
 * @version 0.1
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByMatricule(String matricule);


}
