package si.fri.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "weather_data")
public class WeatherDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    OffsetDateTime timestamp;

    @Column(name = "rainfall_mm")
    Float rainfallmm;

    @Column(name = "et_ref")
    Float etRef;

    Float humidity;

    Float temperature;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserDataEntity user;


    @PrePersist
    public void prePersist() {
        timestamp = OffsetDateTime.now(ZoneOffset.UTC);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        WeatherDataEntity that = (WeatherDataEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}