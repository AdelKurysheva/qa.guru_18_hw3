import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutorizationForm {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void beforeEach() {
        Configuration.browser = "firefox";
    }

    @Test
    void testForm() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");


        $("#firstName").setValue("Inna");
        $("#lastName").setValue("Ivanova");
        $("#userEmail").setValue("i.ivanova@mail.com");
        $("label[for=gender-radio-2]").click();
        $("#userNumber").setValue("8888888888");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("September");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day--008").click();

        $("#subjectsInput").setValue("Math").pressEnter();
        $("label[for=hobbies-checkbox-3]").click();
        $("#uploadPicture").uploadFromClasspath("img/1.png");
        $("#currentAddress").setValue("34 St-Herald Sq, 5th Avenue, 350, New York");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Agra")).click();

        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Inna Ivanova"),
                text("i.ivanova@mail.com"),
                text("Female"),
                text("8888888888"),
                text("8 September,1999"),
                text("Maths"),
                text("Music"),
                text("1.png"),
                text("34 St-Herald Sq, 5th Avenue, 350, New York"),
                text("Uttar Pradesh Agra"));
        $("#closeLargeModal").click();

    }
}
