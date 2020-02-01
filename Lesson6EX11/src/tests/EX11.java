package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import org.junit.Test;
import lib.ui.factories.SearchPageObjectFactory;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.Platform;


public class EX11 extends CoreTestCase{

    private static final String name_of_folder = "Test";

    @Test
    public void EX11() {

        String article_one = "Java";
        String article_two= "JavaScript";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        //Поиск article_one
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(article_one);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }
        else {
            ArticlePageObject.clickContentsOption();
            ArticlePageObject.waitForTitleContents("Java (programming language)");
            ArticlePageObject.closeContents();
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();
        if(Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }

        //Поиск article_two
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(article_two);
        SearchPageObject.clickByArticleWithSubstring("JavaScript");


        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToExistingList(name_of_folder);
        }
        else {
            ArticlePageObject.clickContentsOption();
            ArticlePageObject.waitForTitleContents("JavaScript");
            ArticlePageObject.closeContents();
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        if(Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        else{
            MyListsPageObject.closeSyncArticlesPopup();
        }

        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");
        MyListsPageObject.openArticleByTitle("JavaScript");

        String title_article_two;
        if(Platform.getInstance().isAndroid()) {
            title_article_two = ArticlePageObject.getArticleTitle();
        }
        else {
            ArticlePageObject.clickContentsOption();
            title_article_two = ArticlePageObject.getAttributeArticleTitleFromContents("JavaScript");
        }

        assertEquals( "We see unexpected title!",
                title_article_two,
                article_two);
    }

}
