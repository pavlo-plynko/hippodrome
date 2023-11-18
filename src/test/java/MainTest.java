import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


@Disabled
public class MainTest {

    @Test
    @Timeout(value = 22)
    void mainDurationLimitationTest() throws Exception {
        Main.main(null);
    }
}
