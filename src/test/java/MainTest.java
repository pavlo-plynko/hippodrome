import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Test
    @Disabled
    @Timeout(22)
    void mainDurationLimitTest() throws Exception {
        Main.main(new String[0]);
    }
}
