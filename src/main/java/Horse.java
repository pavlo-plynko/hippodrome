import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

public class Horse {
    private static final Logger logger = LogManager.getLogger(Horse.class);
    private final String name;
    private final double speed;
    private double distance;


    public Horse(String name, double speed, double distance) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        if (isNull(name)) {
            logger.error("{} ERROR Horse: Name is null", formattedDateTime);
            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {
            logger.error("{} ERROR Horse: Name is blank", formattedDateTime);
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            logger.error("{} ERROR Horse: Speed is negative", formattedDateTime);
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            logger.error("{} ERROR Horse: Distance is negative", formattedDateTime);
            throw new IllegalArgumentException("Distance cannot be negative.");
        }
        this.name = name;
        this.speed = speed;
        this.distance = distance;
        logger.info("{} DEBUG Horse: створення Horse, ім'я {}, швидкість {}", formattedDateTime, this.name, this.speed);
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}
