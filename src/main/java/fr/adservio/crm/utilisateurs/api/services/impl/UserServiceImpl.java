package fr.adservio.crm.utilisateurs.api.services.impl;

import fr.adservio.crm.utilisateurs.api.domain.User;
import fr.adservio.crm.utilisateurs.api.repositories.UserRepository;
import fr.adservio.crm.utilisateurs.api.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation of UserService interface
 *
 * @author adservio
 * @version 0.1
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Page<User> findAllUsers(int page, int pageSize) {
        return userRepository.findAll(PageRequest.of(page, pageSize));
    }


    @Override
    public Optional<User> findUserByMatricule(String matricule) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return Optional.empty();
    }

    @Override
    public List<User> findAllUsersByGivenManager(String managerId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public void removeUserById(Long usedId) {
        userRepository.deleteById(usedId);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User createUser(User newUser) {
        return null;
    }
}
