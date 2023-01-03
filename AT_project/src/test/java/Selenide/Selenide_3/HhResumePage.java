package Selenide.Selenide_3;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

public class HhResumePage {

    private final SelenideElement gender = $x("//span[@data-qa='resume-personal-gender']");
    private final SelenideElement age = $x("//span[@data-qa='resume-personal-age']/span");
    private final SelenideElement liveString = $x("//span[@data-qa='resume-personal-address']/ancestor::p");
    private final SelenideElement confirmedNumber = $x("//div[@data-qa='resume-contacts-phone']//span[1]");
    private final SelenideElement city = $x("//span[@data-qa='resume-personal-address']");

    /**
     * Константы для обозначения ключей для хэш карты
     */
    public static String GENDER = "Пол";
    public static String CITY = "Город";
    public static String AGE = "Возраст";
    public static String RELOCATE = "Готовность к переезду";
    public static String CONFIRMED_PHONE = "Подтрвежденный номер телефона";


    public Map<String, Object> getAttributes() {
        return new HashMap<>() {{
            put(GENDER, getGenderHard());
            put(AGE, getAge());
            put(CITY, getCity());
            put(CONFIRMED_PHONE, isPhoneConfirmed());
            put(RELOCATE, isReadyToRelocate());
        }};

    }

    public HhResumePage(String url) {
        Selenide.open(url);
    }

    public String getGender() {
        String genderValue = gender.getText();
        if (genderValue == "Мужчина") {
            return "M";
        }
        return "Ж";

    }

    public String getGenderHard() {
        return gender.getText().equals("Мужчина") ? "М" : "Ж";
    }

    public int getAge() {
        return Integer.parseInt(age.getText().replaceAll("\\D+", ""));
    }

    public String getCity() {
        return city.getText();
    }

    public boolean isReadyToRelocate() {
        return !liveString.getText().split(", ")[1].equals("не готов к переезду");
    }

    public boolean isPhoneConfirmed() {
        return confirmedNumber.isDisplayed();
    }

}
