package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


abstract public class SearchPageObject extends MainPageObject {

     protected static String
    SEARCH_INIT_ELEMENT,
    SEARCH_INPUT,
    SEARCH_CANCEL_BUTTON,
    SEARCH_RESULT_BY_SUBSTRING_TPL,
    SEARCH_RESULT_ELEMENT,
    SEARCH_EMPTY_RESULT_ELEMENT,
    SEARCH_RESULT_BY_ID,
    SEARCH_BY_TITLE_OR_DESCRIPTION,
    SEARCH_RESULTS_ELEMENTS,
    SEARCH_CLEAR,
    SEARCH_RESULTS_LIST,
    OPTIONS_CHANGE_LANGUAGE;




    public SearchPageObject(AppiumDriver driver)
{
    super(driver);
}
/* TEMPLATES METHODS */

private static String getResultSearchElement(String substring)
{
    return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
}


    private static String getTitleAndDescription(String title_or_description)
    {
        return SEARCH_BY_TITLE_OR_DESCRIPTION.replace("{TITLE_OR_DESCRIPTION}", title_or_description);
    }

/* TEMPLATES METHODS */





public void initSearchInput()
{
    this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    this.waitForElementPresent(SEARCH_INPUT,"Cannot find search input after clicking search init element");
}

public void waitForCancelButtonToAppear()
{
    this.waitForElementPresent(SEARCH_CANCEL_BUTTON,"Cannot find search cancel button", 5);
}

public void waitForCancelButtonToDissappear()
{
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Search cancel button is still present ", 5);
}

public void clickCancelSearch()
{
    this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button",5);
}

public void typeSearchLine(String search_line)
{
    this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
}

public void waitForSearchResult(String substring)
{
    String search_result_xpath = getResultSearchElement(substring);
    this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring" + substring);
}

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);

    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    public void assertTheresNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results ");
    }

    public String waitArticleAndGetValueOfAtribute(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        WebElement titleItem = this.waitForElementPresent(
                searchResultXpath,
                "Cannot find search result with substring '" + substring + "'", 15);
        return titleItem.getAttribute("text");
    }

    public void waitToDisappearResultOfSearch(){
        waitForElementNotPresent(SEARCH_RESULT_BY_ID, "Result of search by ID is still present on the page", 5);
    }


    public void waitForSearchResultsDisappear()                     // Метод проверки отсутствия рез-тов поиска (по блокам)
    {
        this.waitForElementNotPresent(SEARCH_RESULTS_LIST, "Search result list is still present",5);
    }



    public String clickArticleBySubstring(String substring){
        String searchResultXpath = getResultSearchElement(substring);
        WebElement articleItem = this.waitForElementAndClick(
                searchResultXpath,
                "Cannot find and click search result with substring " + substring,10);
        return articleItem.getAttribute("text");
    }


    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String article_title = getTitleAndDescription(title);
        this.waitForElementPresent(article_title, "Cannot find article title", 10);
        String article_description = getTitleAndDescription(description);
        this.waitForElementPresent(article_description, "Cannot find article description", 10);
    }

    public List<WebElement> waitForAllResultsPresent(String error_message)      // Метод получения всех результатов поиска
    {
        return this.waitForElementsPresent(SEARCH_RESULTS_ELEMENTS, error_message,15);
    }

    public void searchClearIOS()                                  // Метод очистки поля поиска в ios
    {
        this.waitForElementAndClick(SEARCH_CLEAR, "Cannot find ios search clear 'x' button", 5);
    }




}
