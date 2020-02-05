package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    private static final String login = "Ngorbachev";
    private static final String password = "A123456789a";

    @Test
    public void testSaveFirstAricleToMyList()
    {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMw()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAutButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login ",
            article_title,
            ArticlePageObject.getArticleTitle()
            );
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);


        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);

    }

}
