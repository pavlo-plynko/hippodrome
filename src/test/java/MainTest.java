import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class MainTest {
    @Disabled
    @Test
    @DisplayName("Check, if method is running more that 21 sec.")
    public void MainTimeOut_Exceeded(){
        String[] args = {};
        Main main = new Main();
        assertTimeout(
                ofSeconds(21),
                () -> {
                    Main.main(args);
                });
    }
}
