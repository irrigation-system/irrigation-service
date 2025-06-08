package si.fri.mqtt.receiver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import si.fri.entities.SensorDataEntity;
import si.fri.repositories.SensorDataRepository;

import java.util.logging.Logger;

@ApplicationScoped
public class SensorDataMqttReceiver {

    private static final Logger logger = Logger.getLogger(SensorDataMqttReceiver.class.getName());

    @Inject
    SensorDataRepository sensorDataRepository;

    private final ObjectMapper objectMapper;

    public SensorDataMqttReceiver() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Incoming("sensor-data")
    public void receiveMeasurement(byte[] byteArray) {
        try {
            String messageString = new String(byteArray);
            logger.info("Received raw message: " + messageString);

            SensorDataEntity data = objectMapper.readValue(messageString, SensorDataEntity.class);

            logger.info("Deserialized data: " + data.toString());

            sensorDataRepository.saveForUser(data);

        } catch (Exception e) {
            logger.severe("Failed to deserialize MQTT message: " + e.getMessage());
        }


    }

}