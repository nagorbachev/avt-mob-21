package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {

   protected static String
    FOLDER_BY_NAME_TPL,
    ARTICLE_BY_TITLE_TPL,
    FOLDER_BY_IMAGE_ID,
    ARTICLE_BY_ID,
    CLOSE_SYNC_POP_UP,
    CLICK_ARTICLE_MY_LIST;


    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name"+ name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(article_xpath,"Cannot find saved article by title" + article_title, 15);
    }


    public void waitForArticleToDissappearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(article_xpath,"Saved article still present with title" + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXpathByName(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
        if(Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        this.waitForArticleToDissappearByTitle(article_title);
    }

    public void openFolderByImageID(){
        this.waitForElementAndClick(FOLDER_BY_IMAGE_ID, "Cannot find 'ReadLater' folder by ImageID", 5);
    }

    public void openArticle(){
        this.waitForElementAndClick(ARTICLE_BY_ID, "Cannot find a title of the article", 15);
    }

    public void clicklArticleMyList()
    {
        this.waitForElementAndClick(
                CLICK_ARTICLE_MY_LIST,
                "Cannot open article",
                10);
    }

    public void closeSyncArticlesPopup(){
        this.waitForElementAndClick(CLOSE_SYNC_POP_UP, "Can not find and click close button for 'Sync your saved articles' pop-up", 5);
    }

    public void openArticleByTitle (String article_title) {
        String article_xpath= getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Can not find and open article at list",
                5
        );
    }

}