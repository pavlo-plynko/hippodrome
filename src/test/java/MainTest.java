import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import ru.javarush.mukhametzyanov.hippodrome.Main;

import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Timeout(value = 22000, unit = TimeUnit.MILLISECONDS)
    public void mainMethod_checkTime_timeIsCorrect() {
        try {
            Main.main(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}