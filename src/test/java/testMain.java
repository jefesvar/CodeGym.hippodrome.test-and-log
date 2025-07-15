import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

public class testMain {

    @Disabled("Test is temporarily disabled.")
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void timeOut() {
        try {
            Main.main(new String[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
