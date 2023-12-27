import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

class MainTest {

    @Test
    @Disabled
    @DisplayName("Main_Method_Duration")
    void mainMethodDurationTest() {
        try {
            assertTimeout(Duration.ofSeconds(22), () -> Main.main(null));
        } catch (Exception ignored) {}
    }
}