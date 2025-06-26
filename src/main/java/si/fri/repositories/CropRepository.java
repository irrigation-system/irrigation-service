package si.fri.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import si.fri.entities.CropEntity;

import java.util.UUID;

@ApplicationScoped
public class CropRepository implements PanacheRepositoryBase<CropEntity, UUID> {

}
