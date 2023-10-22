
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static java.util.Objects.isNull;

public class Hippodrome {
    private static final Logger logger=LoggerFactory.getLogger(Hippodrome.class);
    public static final String LIST_IS_NULL="Horses list is null";
    public static final String LIST_IS_EMPTY="Horses list is empty";
    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            logger.error(LIST_IS_NULL);
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            logger.error(LIST_IS_EMPTY);
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
        logger.debug(String.format("створення Hippodrome, коней [%d]", horses.size()));
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
