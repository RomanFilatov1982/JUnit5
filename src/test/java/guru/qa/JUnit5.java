package guru.qa;

import com.codeborne.selenide.Configuration;
import guru.qa.date.Languages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class JUnit5 {
    @BeforeEach
    void setUp() {
        open("https://www.booking.com");
        Configuration.pageLoadTimeout = 60000;
        Configuration.holdBrowserOpen = true;
    }

    @ParameterizedTest(name = "Переключение мужду кнопками бронирования")
    @Tag("BLOCKER")
    @DisplayName("Переключение мужду разными вариантами бронирования")
    void successSelectionRent(List<String> buttonsRent) {
        $(".d24b1ed1cf ").find(byText("Аренда автомобилей")).click();
        $("#main h1").shouldHave(text("Аренда автомобилей для любой поездки"));
    }
    @EnumSource(Languages.class)
    @ParameterizedTest
    void bookingSiteShouldDisplayCorrectText(Languages language) {
        $("[data-testid=header-language-picker-trigger]").click();
        $("#header_language_picker").find(byText(language.name())).click();
        $("#indexsearch").$("[data-testid = herobanner-title1]").shouldHave(text(language.description));
    }
}

