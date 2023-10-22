import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.util.Objects.isNull;

public class Horse {
    private static final Logger logger=LoggerFactory.getLogger(Horse.class);
    public static final String NAME_IS_NULL="Name is null";
    public static final String NAME_IS_BLANK="Name is blank";
    public static final String NEGATIVE_SPEED="Speed is negative";
    public static final String NEGATIVE_DISTANCE="Distance is negative";
    public static final String CREATE_HORSE="створення %s, ім'я [%s], швидкість [%.1f]";
    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {
            logger.error(NAME_IS_NULL);
            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {
            logger.error(NAME_IS_BLANK);
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            logger.error(NEGATIVE_SPEED);
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            logger.error(NEGATIVE_DISTANCE);
            throw new IllegalArgumentException("Distance cannot be negative.");
        }
        this.name = name;
        this.speed = speed;
        this.distance = distance;
        logger.debug(String.format(CREATE_HORSE, getClass().getSimpleName(), name, speed));
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
