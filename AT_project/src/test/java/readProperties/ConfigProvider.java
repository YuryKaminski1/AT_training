package readProperties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {

    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");

    }

    String URL = readConfig().getString("url");
    Integer AGE = readConfig().getInt("age");
    String ADMIN_LOGIN = readConfig().getString("usersParams.admin.login");
    String DEMO_PASSWORD = readConfig().getString("usersParams.demo.password");
    Boolean IS_DEMO_ADMIN = readConfig().getBoolean("usersParams.demo.isAdmin");

    /**
     * данные для задачи по Selenium
     */

    String URL_SELENIUM = readConfig().getString("url_selenium");

    String DEMO_LOGIN_SELENIUM = readConfig().getString("usersParams.demo1.login");
    String DEMO_PASSWORD_SELENIUM = readConfig().getString("usersParams.demo1.password");
    Boolean IS_DEMO_ADMIN_SELENIUM = readConfig().getBoolean("usersParams.demo1.isAdmin");


}
