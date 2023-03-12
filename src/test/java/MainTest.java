import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;



class MainTest {

    @Test
    @Disabled
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @DisplayName("Fails if main method execution exceeds 22 sec")
    void failsIfMainExecutionTimeTimeOut() throws Exception {
        Main mainClass = new Main();
        mainClass.main(new String[]{});
    }
}
