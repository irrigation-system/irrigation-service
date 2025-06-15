package si.fri.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import si.fri.entities.UserDataEntity;
import si.fri.repositories.UserDataRepository;

@ApplicationScoped
public class UserService {

    @Inject
    UserDataRepository repository;

    public UserDataEntity getUserByToken(String token) {
        return repository.findByUserToken(token);
    }

}
