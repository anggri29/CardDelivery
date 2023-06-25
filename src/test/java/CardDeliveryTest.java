import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

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

    private String getFutureDate(int daysToAdd) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = futureDate.format(formatter);
        return formattedDate;
    }

    @Test
    public void shouldTest() throws InterruptedException {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").setValue(getFutureDate(3));
        $("[data-test-id=name] input").setValue("Григорян Ангелина");
        $("[data-test-id=phone] input").setValue("+792508815");
        $("[data-test-id=agreement]").click();
        $("button").click();

        $(withText(" Успешно!")).shouldBe(Condition.hidden, Duration.ofSeconds(15));
    }
}