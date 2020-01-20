package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class RefactoringEx8 extends CoreTestCase
{


    @Test
    public void testSearchEx3() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Jenkins");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String textTitle = SearchPageObject.waitArticleAndGetValueOfAtribute("Jenkins");
        assertEquals("We see unexpected text in title item","Jenkins",textTitle);
        System.out.println("Correct! " + textTitle);

        textTitle = SearchPageObject.waitArticleAndGetValueOfAtribute("Jenkins (software)");
        assertTrue("We see unexpected text in title item_1 'Jenkins (software)'",textTitle.equals("Jenkins (software)"));
        System.out.println("Correct! " + textTitle);

        textTitle = SearchPageObject.waitArticleAndGetValueOfAtribute("Jenkins County, Georgia");
        assertTrue("We see unexpected text in title item_1 'Jenkins County, Georgia'",textTitle.equals("Jenkins County, Georgia"));
        System.out.println("Correct! " + textTitle);

        SearchPageObject.clickCancelSearch();
        System.out.println("Search canceled");
        SearchPageObject.waitToDisappearResultOfSearch();
    }

    @Test
    public void testArticleEx6()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleBySubstring("Java");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertElementPresent("Cannot find title");
        System.out.println("Title is here");
    }

    @Test
    public void testEx5()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleBySubstring("Java");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Test";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("JavaScript");

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToMyCreatedList();
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        String article_one = "Java";
        String article_two = "JavaScript";
        MyListsPageObject.swipeByArticleToDelete(article_two);
        MyListsPageObject.clicklArticleMyList();

        assertEquals(
                "We see unexpected title!",
                "Java",
                article_one
        );

    }




}
