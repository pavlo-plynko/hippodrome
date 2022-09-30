import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void mainTest() {
        try {
            Main.main(null);
        } catch (Exception exception) {
            System.out.println("Something went wrong " + exception);
        }
    }
}