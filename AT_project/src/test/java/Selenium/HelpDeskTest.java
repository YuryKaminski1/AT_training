package Selenium;

import Helpers.TestValues;
import org.testng.Assert;
import org.testng.annotations.Test;
import readProperties.ConfigProvider;

import static Helpers.StringModifier.getUniqueString;

public class HelpDeskTest extends BaseSeleniumTest {

    @Test
    public void checkTicket() {

        String title = getUniqueString(TestValues.TEST_TITLE);
//        String body = "Hello, guys";
//        String email = "testEmail@test.ru";

        TicketPage ticketPage = new MainPage().createTicket(title, TestValues.TEST_BODY, TestValues.TEST_EMAIL)
                .openLoginPage()
                .auth(ConfigProvider.DEMO_LOGIN_SELENIUM, ConfigProvider.DEMO_PASSWORD_SELENIUM)
                .findTicket(title);

        Assert.assertTrue(ticketPage.getTitle().contains(title));
        Assert.assertEquals(ticketPage.getBody(), TestValues.TEST_BODY);
        Assert.assertEquals(ticketPage.getEmail(), TestValues.TEST_EMAIL);

    }


}
