import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Test
    @Timeout(value = 22)
    @Disabled
    @DisplayName("main method execution is within 22 sec")
    void testShouldCheckExecutionTimeIsNotOverLimit() throws Exception {
        Main.main(null);
    }
}
