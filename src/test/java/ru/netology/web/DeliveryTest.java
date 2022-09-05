package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.web.domain.RegistrationInfo;
import ru.netology.web.utils.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {
    RegistrationInfo person;

    @BeforeEach
     void shouldGenerateUsingUtils() {
        person = DataGenerator.Registration.generateInfo("ru");
    }

    @Test
    void shouldFormDeliveryPositive() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue(person.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(person.getDateFirst());
        $("[data-test-id='name'] input").setValue(person.getName());
        $("[data-test-id='phone'] input").setValue(person.getPhone());
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id='success-notification']").shouldBe(appear);
        $("[data-test-id='success-notification']").shouldHave(text(person.getDateFirst()));
        $("[data-test-id='success-notification'] .icon_name_close").click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(person.getDateSecond());
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id='replan-notification']").shouldBe(appear);
        $$("button").find(exactText("Перепланировать")).click();
        $("[data-test-id='success-notification']").shouldBe(appear);
        $("[data-test-id='success-notification']").shouldHave(text(person.getDateSecond()));
    }

}
