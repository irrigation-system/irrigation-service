package si.fri.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import si.fri.entities.UserDataEntity;

@ApplicationScoped
public class UserDataRepository implements PanacheRepository<UserDataEntity> {

    public UserDataEntity findByUserToken(String userToken) {
        return find("userToken", userToken)
                .firstResultOptional()
                .orElseThrow(() -> new NoResultException("User with token '" + userToken + "' does not exist."));
    }
}
