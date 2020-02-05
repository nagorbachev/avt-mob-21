package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:div[class='page-heading']>h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions-watch>#ca-watch";
        OPTION_REMOVE_TO_MY_LIST_BUTTON = "css:#ca-watch[title='Remove this page from your watchlist']";
    }


    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }



}
