package si.fri.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import si.fri.entities.SensorDataEntity;
import si.fri.entities.UserDataEntity;

@ApplicationScoped
public class SensorDataRepository implements PanacheRepository<SensorDataEntity> {

    @Inject
    UserDataRepository userDataRepository;

    @Transactional
    public void saveForUser(SensorDataEntity entity) {
        UserDataEntity user = userDataRepository.findByUserToken(entity.getUser().getUserToken());
        entity.setUser(user);
        persist(entity);
    }

}
