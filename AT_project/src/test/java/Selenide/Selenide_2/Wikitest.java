package Selenide.Selenide_2;

import Selenide.Selenide_1.BaseTest;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

public class Wikitest extends BaseTest {

    private final static String URL = "https://ru.wikipedia.org/wiki/Java";

    @Test
    public void openAllHrefs() {
        Selenide.open(URL);
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        List<String> links = new ArrayList<>();
        //1
//        for (int i = 0; i < hrefs.size(); i++) {
//            links.add(hrefs.get(i).getAttribute("href"));
//        }

        //2
//        for (SelenideElement element : hrefs) {
//            links.add(element.getAttribute("href"));
//        }

        //3
        hrefs.stream().forEach(x -> links.add(x.getAttribute("href")));

        //1
        links.forEach(Selenide::open);

        //2
//        for (int i = 0; i < links.size(); i++) {
//            String listUrl = links.get(i);
//            Selenide.open(listUrl);
//            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
//            Assert.assertEquals(currentUrl, listUrl);
//
//        }

        /**
         * Random
         */
//        Random random = new Random();
//        while (links.size() > 0) {
//            int randomNumber = random.nextInt(links.size());
//            Selenide.open(links.get(randomNumber));
//            links.remove(WebDriverRunner.getWebDriver().getCurrentUrl());
//        }

        List<Integer> linksLenght = hrefs.stream().map(x->x.getAttribute("href").length()).collect(Collectors.toList());

        int a = 0;

    }
}
