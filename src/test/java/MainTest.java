import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MainTest {

    @Test
    @Timeout(22)
    @Disabled("Вимкнено за умовою завдання, можна запускати вручну за потреби")
    void mainShouldRunUnder22Seconds() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
