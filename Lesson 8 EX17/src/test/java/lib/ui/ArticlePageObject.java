package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertTrue;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
            TITLE,
            TITLE_ON_LIST,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTION_REMOVE_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON ,
            CLOSE_ARTICLE_BUTTON,
            FOLDER_XPATH_TMP, //ReadLater button
            TITLE_XPATH_TMP, //Java (programming language) title
            MY_LISTS_LINK,
            MY_CREATED_LIST,
            TITLE_TEXT_TPL,
            OPTIONS_CONTENTS_BUTTON,
            TITLE_FROM_CONTENTS_TPL,
            CLOSE_CONTENTS_BUTTON,
            FIRST_WORD_AT_TITLE,
            OPTIONS_CHANGE_LANGUAGE_BUTTON,
            OPTIONS_SHARE_LINK_BUTTON,
            OPTIONS_FIND_IN_PAGE_BUTTON,
            OPTIONS_FONT_AND_THEME_BUTTON,
            EXISTING_LIST_LINK_TPL;





    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getFolderXpath(String substring) {
        return FOLDER_XPATH_TMP.replace("{FOLDER_NAME}", substring);
    }
    private static String getTitleXpath(String substring) {
        return TITLE_XPATH_TMP.replace("{TITLE}", substring);
    }

    private static String getTitleElement(String substring)
    {
        return TITLE_TEXT_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getArticleTitleFromContents (String article_title){
        return TITLE_FROM_CONTENTS_TPL.replace("{TITLE}", article_title);
    }

    private static String getListElement (String name_of_list) {
        return EXISTING_LIST_LINK_TPL.replace("{NAME_OF_LIST}", name_of_list );
    }


    /*TEMPLATES METHODS*/



    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE,"Cannot find article title on page", 15);
    }


    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }

    }


    public void waitForNumberOfResultsFound(int number_of_results)
    {
        this.waitForElementSearchResultsArticleByTitle(TITLE, number_of_results,"Found less than "+number_of_results+" articles", 10);
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article ",
                    600
            );
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
            "Cannot find the end of article",
            600);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT,"Cannot find the end of article", 40);
        }

    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
        OPTIONS_BUTTON,
        "Cannot find button to open article options",
        5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder ",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle() {
        if(Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link",
                    5
            );
        } else {
            System.out.println("Method closeArticle() do nothing to platform" + Platform.getInstance().getPlatformVar());
        }



    }


    public void assertElementPresent(String error_message)
    {
        List<WebElement> elements = driver.findElements(By.id(TITLE));
        int article_present = elements.size();
        for(WebElement element: elements)
        {
            String tittle = element.getText();
            String error = error_message + tittle;
            assertTrue (error, article_present > 0);
        }
    }



    public void addArticleToMyCreatedList()
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5);
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                MY_CREATED_LIST,
                "Cannot find the created list to add the article",
                5
        );
    }

    public void clickMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to my list",
                5
        );
    }

    public void addArticlesToMySaved(){
        if (Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,"Cannot find option to add article to readinglist", 5);
    }


    public void removeArticleFromSavedIfItAdded()
    {
        if(this.isElementPresent(OPTION_REMOVE_TO_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(OPTION_REMOVE_TO_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from save",
                    1
            );
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before"
            );
        }
    }


    public WebElement waitForTitleUseXpath(String substring)
    {
        String search_result_xpath = getTitleElement(substring);
        return this.waitForElementPresent(search_result_xpath,"Cannot find article title on page", 10);
    }

    public void clickContentsOption (){
        this.waitForElementAndClick(
                OPTIONS_CONTENTS_BUTTON,
                "Can not find and click Contents button",
                5
        );
    }

    public WebElement waitForTitleContents(String article_title){
        return this.waitForElementPresent(getArticleTitleFromContents(article_title),"Can not find article title from contents: "+article_title, 15);
    }

    public void closeContents(){
        this.waitForElementAndClick(
                CLOSE_CONTENTS_BUTTON,
                "Can not find and click CLose Contents button",
                5
        );
    }


    public String waitForContentOfArticle() {
        WebElement element= this.waitForElementPresent(FIRST_WORD_AT_TITLE, "Can not find first word of ", 10);
        return element.getText();
    }

    public void addArticleToExistingList(String name_of_list) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForOptionsMenuToRender();

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not find option to adding article to reading list",
                5
        );

        this.waitForElementAndClick(
                getListElement(name_of_list),
                "Can not find created folder with name " +name_of_list,
                5
        );
    }

    public void waitForOptionsMenuToRender() {
        waitForElementPresent(
                OPTIONS_CHANGE_LANGUAGE_BUTTON,
                "Can not find option to change language",
                5);
        waitForElementPresent(
                OPTIONS_SHARE_LINK_BUTTON,
                "Can not find option to share article",
                5);
        waitForElementPresent(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not find option to add article to list",
                5);
        waitForElementPresent(
                OPTIONS_FIND_IN_PAGE_BUTTON,
                "Can not find option to find smth at article",
                5);
        waitForElementPresent(
                OPTIONS_FONT_AND_THEME_BUTTON,
                "Can not find option to change font and theme",
                5);
    }


    public String getAttributeArticleTitleFromContents (String article_title) {
        WebElement titleElement = waitForTitleContents(article_title);
        if(Platform.getInstance().isAndroid()){
            return titleElement.getAttribute("text");
        } else {
            return titleElement.getAttribute("name");
        }
    }






}


