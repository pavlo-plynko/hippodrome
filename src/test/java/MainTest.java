import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainTest {
    @Disabled
    @Test
    //@Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void mainTest (){
       Assertions.assertTimeout(Duration.ofSeconds(22),()-> Main.main(new String[1]));
    }
}
