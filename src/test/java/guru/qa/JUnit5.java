package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.date.Languages;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class JUnit5 {
    @BeforeEach
    void setUp() {
        open("https://www.booking.com");
        Configuration.pageLoadTimeout = 60000;
        //Configuration.holdBrowserOpen = true;
    }

    @EnumSource(Languages.class)
    @ParameterizedTest
    @Tag("SMOKE")
    void bookingSiteShouldDisplayCorrectText(Languages language) {
        $("[data-testid=header-language-picker-trigger]").click();
        $("#onetrust-accept-btn-handler").click();
        $("#header_language_picker").find(byText(language.name)).click();
        $("[data-testid = herobanner-title1]").shouldHave(text(language.description));
    }


    static Stream<Arguments> bookingSiteShouldHaveCorrectNameButtons() {
        return Stream.of(
                Arguments.of(
                        Languages.DEUTSCH,
                        List.of("Vuelos", "Flüge", "Vuelo + Hotel", "Alquiler de coches", "Atracciones", "Taxis aeropuerto")),
                Arguments.of(
                        Languages.ENGLISH,
                        List.of("Stays", "Flight", "Flight + Hotel", "Car rental", "Attractions", "Airport taxis")),
                Arguments.of(
                        Languages.POLSKI,
                        List.of("Pobyty", "Loty", "Lot + Hotel", "Wynajem samochodu", "Atrakcje", "Taksówki lotniskowe"))
        );
    }

    @MethodSource
    @ParameterizedTest
    @Tag("WEB")
    void bookingSiteShouldHaveCorrectNameButtons(Languages language, List<String> buttonsRent) {
        $("[data-testid=header-language-picker-trigger]").click();
        $("#header_language_picker").find(byText(language.name)).click();
        $$(".Header_tab ").filter(visible).shouldHave(texts(buttonsRent));
    }

    @CsvSource(value = {
            "Авиабилеты, Сравнивайте и бронируйте дешевые авиабилеты — это легко",
            "Авиабилеты + отели, Je volledige vakantie in één klik",
            "Аренда автомобилей, Аренда автомобилей для любой поездки",
            "Варианты досуга, Экскурсии и развлечения",
            "Такси от/до аэропорта, Забронируйте такси от/до аэропорта"
    })

    @ParameterizedTest(name = "Переключение между кнопками {0} и проверки заголовка {1}")
    @Tag("BLOCKER")
    void successSelectionRent(String button, String title) {
        $("[data-testid= header-xpb]").find(byText(button)).click();
        $("h1").shouldHave(text(title));
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
}

 /*static Stream<String> menuItems() {
        return Stream.of(
                "Пиццы", "Комбо", "Закуски", "Коктейли", "Кофе",
                "Напитки", "Десерты", "Соусы", "Другие товары", "Новинки",
                "Завтрак", "Ещё", "Акции"
        );
    }

    @ParameterizedTest(name = "Проверка раздела меню: {0}")
    @MethodSource("menuItems")
    void checkMenuItemLoadsCorrectPage(String menuItem) {
        open("https://dodopizza.ru"); // при необходимости добавить регион

        // Пропустить попап/баннер (если есть)
        // $("#close-banner").click();

        // Клик по пункту меню
        $$("nav").findBy(Condition.text(menuItem)).shouldBe(Condition.visible).click();

        // Проверка, что контент страницы соответствует выбранному меню
        // Уточни: можно ли проверять по заголовку (например, h1)
        $("h1, h2, h3").shouldHave(Condition.text(menuItem)); // адаптируй под актуальный селектор
    }
}*/
 /*   void clickAllBookingButtons(List<String> buttonTexts) {
        for (String textValue : buttonTexts) {
            $$(".c4a6e8e871 a").findBy(text(textValue)).shouldBe(visible).click();
        }
    }*/

    /*  @Test
      void bookingSiteShouldDisplayCorrectText() {
          $(".locality-selector-popup__table").find(byText("Кисловодск")).click();
      }*/
