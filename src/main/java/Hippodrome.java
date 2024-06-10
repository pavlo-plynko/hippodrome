import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.isNull;

public class Hippodrome {
    private static final Logger logger = LogManager.getLogger(Hippodrome.class);
    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        if (isNull(horses)) {
            logger.error("{} ERROR Hippodrome: Horses list is null", formattedDateTime);
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            logger.error("{} ERROR Hippodrome: Horses list is empty", formattedDateTime);
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
        logger.debug("{} DEBUG Hippodrome: створення Hippodrome, коней [{}]", formattedDateTime, horses.size());
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
