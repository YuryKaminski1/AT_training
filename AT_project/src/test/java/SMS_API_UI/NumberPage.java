package SMS_API_UI;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class NumberPage {
    private SelenideElement accountBalanceText = $x("//a[@id='balansUser']");
    private SelenideElement phoneIdText = $x("//table[@id='getNumberTable']//tr[2]/td[1]");
    private SelenideElement phoneNumberText = $x("//div[@class='input-group']//input");

    public String getAccountBalanceText() {
        return accountBalanceText.getText();
    }

    public String getPhoneIdText() {
        return phoneIdText.getText();
    }

    public String getPhoneNumberText() {
        return phoneNumberText.getText();
    }
}
