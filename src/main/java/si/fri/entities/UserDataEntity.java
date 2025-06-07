package si.fri.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "user_data")
public class UserDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name="user_token")
    String userToken;
    String irrigationStart;
    String monthlyRainfall;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crop_id", referencedColumnName = "id")
    CropEntity crop;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    LocationEntity location;

}
