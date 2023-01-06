package readProperties;

import Selenide.Selenide_1.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PropertiesTest extends BaseTest {

    @Test
    public void readPropertiesTEst() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));

        String urlFromProperty = System.getProperty("url");
        System.out.println(urlFromProperty);

    }

    @Test
    public void readFromConfTest() {

        String urlFromConf = ConfigProvider.URL;
        System.out.println(urlFromConf);
        Boolean isDemoAdmin = ConfigProvider.IS_DEMO_ADMIN;
        System.out.println(isDemoAdmin);

        if (ConfigProvider.readConfig().getBoolean("usersParams.admin.isAdmin")){
            System.out.println("Admin is admin");
        } else {
            System.out.println(12);
        }


    }
}
