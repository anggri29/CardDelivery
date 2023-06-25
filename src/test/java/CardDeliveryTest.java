import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class CardDeliveryTest {
    @BeforeEach
    void setUp() {
        Configuration.headless = true;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);
        open("http://localhost:9999/");
    }

    @Test
    public void shouldTest() throws InterruptedException {
        $("[data-test-id=name] input").sendKeys("Григорян Ангелина");
        $("[data-test-id=phone] input").sendKeys("+79250881558");
        $("[data-test-id=agreement]").click();
        $("button").click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = $("[data-test-id=order-success]").getText().trim();
        assertEquals(expected, actual);
        Thread.sleep(5000);
    }
}
