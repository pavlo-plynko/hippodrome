import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;


public class MainTest {
    @Test
    @Disabled
    void checkTimeofMain() {
        Assertions.assertTimeout(Duration.ofSeconds(22), () -> Main.main(null));
    }
}

