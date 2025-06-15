package si.fri.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "crop")
public class CropEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String name;

    @Column(name = "min_allowed_moisture")
    private Float minAllowedMoisture;

    @Column(name = "coefficient_dev")
    private Float coefficientDev;

    @Column(name = "coefficient_mid")
    private Float coefficientMid;

    @Column(name = "coefficient_late")
    private Float coefficientLate;

    @Column(name = "dev_num_of_days")
    private Long devNumOfDays;

    @Column(name = "mid_num_of_days")
    private Long midNumOfDays;

    @Column(name = "lat_num_of_days")
    private Long latNumOfDays;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CropEntity that = (CropEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
