package SMS_API_UI;

import Selenide.Selenide_1.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.$x;

public class SmsTest extends BaseTest {

    @BeforeTest
    public void setUpExtension() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addExtensions(new File("C:\\Users\\Yury_Kaminski1\\AT_training\\AT_project\\src\\test\\resources\\modheader.crx"));
        Configuration.browserCapabilities = new DesiredCapabilities();
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    }

    private void addCookie() {
        Selenide.open("http://2ip.ru");
        Selenide.open("chrome-extension://idgpnmonknjnojddfkpgkljpfnnfcklj/popup.html");
        $x("//input[@placeholder='Name']").sendKeys("cookie");
        $x("//input[@placeholder='Value']").sendKeys("lang=ru; _ga=GA1.2.69770897.1637760369; _ym_uid=1637760369714540380; _ym_d=1637760369; _gid=GA1.2.873034881.1645195768; _ym_isad=1; session=c506ba4443b0042f14f95cb74254c331; userid=14734; PHPSESSID=pogdg4ik8ojli5j2kb82lgvnod; _ym_visorc=w");
        $x("//input[@placeholder='Name']").click();
    }

    @Test
    public void checkBalance() {
        Selenide.open("http://smshub.org/ru/activations");
        SmsApi smsApi = new SmsApi();
        NumberPage numberPage = new NumberPage();
        String balanceApi = smsApi.getAccountBalance();
        String balanceUi = numberPage.getAccountBalanceText();
        Assert.assertTrue(balanceUi.contains(balanceApi));
    }

    @Test
    public void numberCHeck() {
        Selenide.open("http://smshub.org/ru/activations");
        SmsApi smsApi = new SmsApi();
        NumberPage numberPage = new NumberPage();
        PhoneData phoneData = smsApi.getPhone();
        String apiId = phoneData.getNumberId();
        String apiPhone = phoneData.getPhoneNumber();

        String uiId = numberPage.getPhoneIdText();
        String uiPhone = numberPage.getPhoneNumberText();
        Assert.assertEquals(apiId, uiId);
        Assert.assertEquals(apiPhone, uiPhone);
    }
}

