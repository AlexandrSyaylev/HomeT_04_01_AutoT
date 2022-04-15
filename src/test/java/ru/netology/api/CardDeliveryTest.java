package ru.netology.api;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.util.concurrent.TimeUnit.SECONDS;

public class CardDeliveryTest {


    @Test
    public void ShouldSubmitRequest() {
        open("http://localhost:9999/");
        SelenideElement form = $("form[enctype='application/x-www-form-urlencoded']");
        $("input[placeholder='Город']").setValue("Архангельск");
        $("input.input__control[placeholder='Дата встречи']").setValue("19.04.2022");
        $("input[name='name']").setValue("Валенков Алеша");
        $("input[name='phone']").setValue("+79008006600");
        $("label[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        Configuration.timeout = 15000;
        $(withText("Успешно!")).shouldBe(visible);
    }
}
