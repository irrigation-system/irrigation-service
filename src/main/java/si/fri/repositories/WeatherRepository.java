package si.fri.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import si.fri.entities.UserDataEntity;
import si.fri.entities.WeatherDataEntity;

@ApplicationScoped
public class WeatherRepository implements PanacheRepository<WeatherDataEntity> {

    @Inject
    UserDataRepository userDataRepository;

    @Transactional
    public void saveForUser(WeatherDataEntity entity) {
        UserDataEntity user = userDataRepository.findByUserToken(entity.getUser().getUserToken());
        entity.setUser(user);
        persist(entity);
    }

    public WeatherDataEntity findLatest(UserDataEntity user) {
        return find("user = ?1 order by timestamp desc", user)
               .firstResult();
    }
}