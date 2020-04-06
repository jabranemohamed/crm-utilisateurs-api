package fr.adservio.crm.utilisateurs.api.services;

import fr.adservio.crm.utilisateurs.api.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * User service interface which define all business methods related to User useCases
 */
public interface UserService {

    Page<User> findAllUsers(int page, int pageSize);

    Optional<User> findUserByMatricule(String matricule);

    Optional<User> findUserById(Long userId);

    Optional<User> findByUserName(String userName);

    List<User> findAllUsersByGivenManager(String managerId);

    void deleteUser(String userId);

    void removeUserById(Long usedId);

    void update(User user);

    User createUser(User newUser);
}
