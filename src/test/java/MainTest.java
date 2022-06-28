import org.junit.jupiter.api.*;

public class MainTest {
    @Disabled
    @Test
    @DisplayName("Check, if method is running more that 21 sec.")
    @Timeout(22)
    public void MainTimeOut_Exceeded() throws Exception {
        Main.main(null);
    }
}
