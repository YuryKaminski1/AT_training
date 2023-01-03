package Selenide.Selenide_1;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

abstract public class BaseTest {

    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    @BeforeClass
    public void init() {
        setUp();
    }

    @AfterClass
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}
