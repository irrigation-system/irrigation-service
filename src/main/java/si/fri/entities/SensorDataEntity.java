package si.fri.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "sensor_data")
public class SensorDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    LocalDateTime timestamp;

    @Column(name = "soil_moisture")
    Float soilMoisture;

    Float tds;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserDataEntity user;

}
