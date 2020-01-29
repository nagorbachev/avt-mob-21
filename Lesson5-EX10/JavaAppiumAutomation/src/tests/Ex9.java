package hw_tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex9 extends CoreTestCase {


    @Test
    public void testSearchByTitleAndDescription()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java","Island of Indonesia");
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript","Programming language");
        SearchPageObject.clickCancelSearch();
        System.out.println("All titles are here");
    }


}