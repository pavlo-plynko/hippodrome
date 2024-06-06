import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Disabled
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void main_failsIfExecutionTimeExceeds22Seconds() {
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException("main runs over 22 seconds");
        }
    }
}