package si.fri.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
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


}
