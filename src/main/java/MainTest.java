import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Disabled
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void mainTime() throws Exception {
        Main.main(new String[1]);
    }
}
