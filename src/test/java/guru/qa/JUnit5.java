package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.date.Languages;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class JUnit5 {
    @BeforeEach
    void setUp() {
        open("https://www.booking.com");
        Configuration.pageLoadTimeout = 60000;
        //Configuration.holdBrowserOpen = true;
    }

    /*  @Test
      void bookingSiteShouldDisplayCorrectText() {
          $(".locality-selector-popup__table").find(byText("Кисловодск")).click();
      }*/
    @EnumSource(Languages.class)
    @ParameterizedTest
    @Tag("SMOKE")
    void bookingSiteShouldDisplayCorrectText(Languages language) {
        $("[data-testid=header-language-picker-trigger]").click();
        $("#onetrust-accept-btn-handler").click();
        $("#header_language_picker").find(byText(language.name)).click();
        $("[data-testid = herobanner-title1]").shouldHave(text(language.description));
    }


        static Stream<Arguments> successSelectionRent() {
            return Stream.of(
            Arguments.of(
                    Languages.DEUTSCH,
                    List.of("Vuelos", "Flüge", "Vuelo + Hotel","Alquiler de coches","Atracciones", "Taxis aeropuerto")),
            Arguments.of(
                    Languages.ENGLISH,
                    List.of("Stays", "Flight", "Flight + Hotel","Car rental","Attractions", "Airport taxis")),
            Arguments.of(
                    Languages.POLSKI,
                    List.of("Pobyty", "Loty", "Lot + Hotel", "Wynajem samochodu", "Atrakcje", "Taksówki lotniskowe"))
            );
        }

   @MethodSource
    @ParameterizedTest
   @Tag("WEB")
    void bookingSiteShouldDisplayCorrectText(Languages language, List<String> buttonsRent) {
        $("[data-testid=header-language-picker-trigger]").click();
        $("#header_language_picker").find(byText(language.name())).click();
        $$(".c4a6e8e871").filter(visible).shouldHave(texts(buttonsRent));
    }
    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    /*    @MethodSource
    @ParameterizedTest(name = "Переключение мужду кнопками бронирования")
    @Tag("BLOCKER")
    @DisplayName("Переключение мужду разными кнопками бронирования")
    void successSelectionRent(Languages language, List<String> ButtonsRent) {
        $("[data-testid=header-language-picker-trigger]").click();
        $("#onetrust-accept-btn-handler").click();
        $("#header_language_picker").find(byText(language.name())).click();
           for (String buttonText : ButtonsRent) {
               $$(".c4a6e8e871 a").find(text(buttonText)).click();
           }
        $("#main h1").shouldHave(text("Аренда автомобилей для любой поездки"));
    }*/
}

