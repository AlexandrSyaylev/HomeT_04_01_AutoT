package ru.netology.api;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.util.concurrent.TimeUnit.SECONDS;

public class CardDeliveryTest {


    @Test
    public void ShouldSubmitRequest() {
        open("http://localhost:9999/");

        //SelenideElement form = $("[form form_size_m form_theme_alfa-on-white]");
        SelenideElement form = $("form[enctype='application/x-www-form-urlencoded']");
        $("input[placeholder='Город']").setValue("Архангельск");
        $("input[type='tel']").setValue("10.05.2022");
        //$(byText("Дата встречи")).setValue("10.05.2022");
        $("input[name='name']").setValue("Валенков Алеша");
        $("input[name='phone']").setValue("+79008006600");
        $("label[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        $("form[enctype='application/x-www-form-urlencoded']").should();

        SelenideElement notific = $("[data-test-id='notification']");
        $(withText("Успешно!")).shouldBe(visible);
        $(withText("Встреча успешно забронирована на ")).shouldBe(visible);

    }
}
