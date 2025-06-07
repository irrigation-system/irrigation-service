package si.fri.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "weather_data")
public class WeatherDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    LocalDateTime timestamp;

    @Column(name = "rainfall_mm")
    Float rainfallmm;

    @Column(name = "et_ref")
    Float etRef;

    Float humidity;

    Float temperature;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserDataEntity user;
}