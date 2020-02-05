package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;




public class EX17 extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    private static final String login = "Ngorbachev";
    private static final String password = "A123456789a";

    @Test
    public void testSave2Articles() {

        String article_one = "Java";
        String article_two ="Appium";

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
        else if (Platform.getInstance().isIOS()){
            ArticlePageObject.clickContentsOption();
            ArticlePageObject.waitForTitleContents("Java (programming language)");
            ArticlePageObject.closeContents();
            ArticlePageObject.addArticlesToMySaved();
        }
        else {
            String first_word_of_article =ArticlePageObject.waitForContentOfArticle();
            assertEquals("We see unexpected title!",
                    "Java",
                    first_word_of_article);
            try {
                Thread.sleep(500);
            }catch(Exception e){}

            ArticlePageObject.addArticlesToMySaved();

            AuthorizationPageObject Auth=new AuthorizationPageObject(driver);

            try {
                Thread.sleep(1000);
            }catch(Exception e){}

            Auth.clickAutButton();

            try {
                Thread.sleep(1000);
            }catch(Exception e){}

            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();


        if(Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }

        //Поиск article_two
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(article_two);
        SearchPageObject.clickByArticleWithSubstring("Appium");


        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToExistingList(name_of_folder);
        }
        else if (Platform.getInstance().isIOS()){
            ArticlePageObject.clickContentsOption();
            ArticlePageObject.waitForTitleContents("Appium");
            ArticlePageObject.closeContents();
            ArticlePageObject.addArticlesToMySaved();
        }
        else{
            String first_word_of_article =ArticlePageObject.waitForContentOfArticle();
            assertEquals("",
                    "Appium",
                    first_word_of_article);

            try {
                Thread.sleep(500);
            }catch(Exception e){}
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        if(Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI= NavigationUIFactory.get(driver);

        try {
            Thread.sleep(100);
        }catch(Exception e){}
        NavigationUI.openNavigation();

        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        else if (Platform.getInstance().isIOS()){
            MyListsPageObject.closeSyncArticlesPopup();
        }


        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");

        MyListsPageObject.openArticleByTitle("Appium");

        String title_article_two;
        if(Platform.getInstance().isAndroid()) {
            title_article_two = ArticlePageObject.getArticleTitle();
        }
        else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.clickContentsOption();
            title_article_two = ArticlePageObject.getAttributeArticleTitleFromContents("Appium");
        }
        else {
            title_article_two= ArticlePageObject.waitForContentOfArticle();
        }

        assertEquals( "We see unexpected title!",
                title_article_two,
                article_two);
    }


}
