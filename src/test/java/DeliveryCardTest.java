import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {
    String planningDate = generateDate(4);



    @Test
    void shouldDeliveryCard(){
//        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

        $("[data-test-id=\"city\"] input").val("Самара");

        $("[data-test-id=\"date\"] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] input").val(planningDate);

        $("[data-test-id=\"name\"] input").val("Иванов Николай");
        $("[data-test-id=\"phone\"] input").val("+79277775544");

        $(byClassName("checkbox__box")).click();
        $(byClassName("button__content")).click();

        $(byClassName("notification__title")).shouldBe(visible, Duration.ofSeconds(14)).shouldHave(text("Успешно!"));
        $("[class='notification__content']")
                .shouldHave(text("Встреча успешно забронирована на " + planningDate));
    }


    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
