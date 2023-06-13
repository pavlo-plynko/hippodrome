import com.sun.jdi.Value;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled("turned it off, as it says in the Task")
    @Timeout(value = 22, unit = SECONDS)
    void main() {

    }
}