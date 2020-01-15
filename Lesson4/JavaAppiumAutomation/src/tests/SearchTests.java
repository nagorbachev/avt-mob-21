package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }


    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchIput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDissappear();
    }




    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchIput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }


    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchIput();
        String search_line = "fdhfhdhfdhf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertTheresNoResultOfSearch();

    }

    @Test
    public void testSearchEx3() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Jenkins");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String textTitle = SearchPageObject.waitArticleAndGetValueOfAtribute("Jenkins");
        assertEquals("We see unexpected text in title item","Jenkins",textTitle);
        System.out.println("Well done! " + textTitle);

        textTitle = SearchPageObject.waitArticleAndGetValueOfAtribute("Jenkins (software)");
        assertTrue("We see unexpected text in title item_1 'Jenkins (software)'",textTitle.equals("Jenkins (software)"));
        System.out.println("Well done! " + textTitle);

        textTitle = SearchPageObject.waitArticleAndGetValueOfAtribute("Jenkins County, Georgia");
        assertTrue("We see unexpected text in title item_1 'Jenkins County, Georgia'",textTitle.equals("Jenkins County, Georgia"));
        System.out.println("Well done! " + textTitle);

        SearchPageObject.clickCancelSearch();
        System.out.println("Search canceled");
        SearchPageObject.waitToDisappearResultOfSearch();
    }

    @Test
    public void testArticleEx6()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchIput();
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
        SearchPageObject.initSearchIput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleBySubstring("Java");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Test folder";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchIput();
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
