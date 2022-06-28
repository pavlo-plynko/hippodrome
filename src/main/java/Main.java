
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws Exception {

        List<Horse> horses = List.of(
                new Horse("Bucefal", 2.4),
                new Horse("Tuz Pic", 2.5),
                new Horse("Zefir", 2.6),
                new Horse("Fire", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegas", 2.9),
                new Horse("Masea", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        LOGGER.info("Start of the race. Number of participants "+horses.size());

        for (int i = 0; i < 2; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println("Win:  " + winnerName + "!");
        LOGGER.info("Finish of the race. Winner: "+winnerName);
    }

    private static void watch(Hippodrome hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }
}
