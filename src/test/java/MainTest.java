import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled
    @Timeout(22)
    void main() {
        PrintStream origin = System.out;
        try {
            System.setOut(new PrintStream(File.createTempFile("temp", ".tmp")));
            Main.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(origin);
        }
    }
}