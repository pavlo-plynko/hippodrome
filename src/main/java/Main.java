import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.logging.Level.INFO;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws Exception {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        logger.info("{}, INFO Main: Початок стрибків. Кількість учасників: {}", formattedDateTime, horses.size());

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println(winnerName + " wins!");
        String formattedDateTimeFinish = LocalDateTime.now().format(formatter);
        logger.info("{}, INFO Main: Закінчення стрибків. Переможець:{}", formattedDateTimeFinish, hippodrome.getWinner().getName());
    }

    private static void watch(Hippodrome hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }
}
