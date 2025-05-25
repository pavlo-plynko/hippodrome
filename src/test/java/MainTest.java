import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

public class MainTest {

    @Disabled
    @Test
    public void checkTimeout() {
        assertTimeout(
                Duration.ofSeconds(22),
                () -> Main.main(new String[0])
        );
    }
}
