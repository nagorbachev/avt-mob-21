package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertTrue;


public class ArticlePageObject extends MainPageObject
{
    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            TITLE_ON_LIST = "id:org.wikipedia:id/page_list_item_title",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            FOLDER_XPATH_TMP = "//*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER_NAME}']",//ReadLater button
            TITLE_XPATH_TMP = "xpath://*[@resource-id='org.wikipedia:id/view_page_header_container']/*[@text='{TITLE}']",//Java (programming language) title
            MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']",
            MY_CREATED_LIST = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text='Test']";




    public ArticlePageObject(AppiumDriver driver)
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
    /*TEMPLATES METHODS*/



    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE,"Cannot find article title on page", 15);
    }

    public WebElement waitForTitleElementOnList()
    {
        return this.waitForElementPresent(TITLE_ON_LIST,"Cannot find article title on list", 15);
    }


    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }


    public void waitForNumberOfResultsFound(int number_of_results)
    {
        this.waitForElementSearchResultsArticleByTitle(TITLE, number_of_results,"Found less than "+number_of_results+" articles", 10);
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article ",
                20
        );
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

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );


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



}


