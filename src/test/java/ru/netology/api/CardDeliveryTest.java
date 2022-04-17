package ru.netology.api;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.awt.SystemColor.menu;
import static java.util.concurrent.TimeUnit.SECONDS;

public class CardDeliveryTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    public void ShouldSubmitRequest() {
        String dateMeetin = generateDate(3);
        open("http://localhost:9999/");
        $("input[placeholder='Город']").setValue("Архангельск");
        $("input.input__control[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        $("input.input__control[placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("input.input__control[placeholder='Дата встречи']").setValue(dateMeetin);
        $("input[name='name']").setValue("Валенков Алеша");
        $("input[name='phone']").setValue("+79008006600");
        $("label[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + dateMeetin));
    }


    @Test
    public void SecondTask() {
        String dateMeetin = generateDate(7);
        System.out.println(dateMeetin);
        open("http://localhost:9999/");
        $("input[placeholder='Город']").click();
        $("input[placeholder='Город']").sendKeys("а", "р");
        $("input[placeholder='Город']").sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        $("button.icon-button").click();
        $("input.input__control[placeholder='Дата встречи']").
                sendKeys(Keys.ARROW_DOWN, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ENTER);
        $("input[name='name']").setValue("Валенков Алеша");
        $("input[name='phone']").setValue("+79008006600");
        $("label[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + dateMeetin));

    }

}
