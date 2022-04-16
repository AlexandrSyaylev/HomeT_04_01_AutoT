package ru.netology.api;

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
    GregorianCalendar date = new GregorianCalendar();
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Test
    public void ShouldSubmitRequest() {
        date.roll(Calendar.DATE, 3);
        String dateMeetin = dateFormat.format(date.getTime());
        System.out.println(dateMeetin);
        open("http://localhost:9999/");
        SelenideElement form = $("form[enctype='application/x-www-form-urlencoded']");
        $("input[placeholder='Город']").setValue("Архангельск");
        $("input.input__control[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        $("input.input__control[placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("input.input__control[placeholder='Дата встречи']").setValue(dateMeetin);
        $("input[name='name']").setValue("Валенков Алеша");
        $("input[name='phone']").setValue("+79008006600");
        $("label[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        Configuration.timeout = 15000;
        $(withText("Успешно!")).shouldBe(visible);
        $(byText("Встреча успешно забронирована на")).shouldBe(visible);
        $(byText(dateMeetin)).shouldBe(visible);
    }

}
