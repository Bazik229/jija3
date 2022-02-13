import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    @Test
    void shouldDeliveryCard(){
//        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");

        ElementsCollection inputs = $$(byClassName("input__control"));
        inputs.get(0).val("Самара");
        inputs.get(1).val("13.02.2022");
        inputs.get(2).val("Иван Петров");
        inputs.get(3).val("+79277163582");

        $(byClassName("checkbox__box")).click();
        $(byClassName("button__content")).click();

        $(byClassName("notification__title")).shouldBe(visible, Duration.ofSeconds(14)).shouldHave(text("Успешно!"));
    }
}
