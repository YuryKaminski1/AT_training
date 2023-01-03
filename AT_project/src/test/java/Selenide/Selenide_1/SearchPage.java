package Selenide.Selenide_1;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

public class SearchPage {

    private final ElementsCollection articleTitle = $$x("//h2//a");

    /**
     * Возвращает href из первой статьи
     * @return
     */
    public String getHrefFromFirstArticle() {
        return articleTitle.first().getAttribute("href");
    }
}
