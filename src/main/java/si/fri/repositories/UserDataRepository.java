package si.fri.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import si.fri.entities.UserDataEntity;

import java.util.UUID;

@ApplicationScoped
public class UserDataRepository implements PanacheRepositoryBase<UserDataEntity, UUID> {

    public UserDataEntity findByUserToken(String userToken) {
        return find("userToken", userToken)
                .firstResultOptional()
                .orElseThrow(() -> new NoResultException("User with token '" + userToken + "' does not exist."));
    }
}
