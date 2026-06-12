import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class AppTest {
    @Test
    public void test(){
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
