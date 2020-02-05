package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button#cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        SEARCH_RESULT_BY_ID = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_BY_TITLE_OR_DESCRIPTION = "xpath://*[@class = 'android.widget.LinearLayout']//*[@text = '{TITLE_OR_DESCRIPTION}']";
        SEARCH_RESULTS_ELEMENTS = "xpath://XCUIElementTypeCollectionView//*[contains(@type,'XCUIElementTypeCell')]";
        SEARCH_CLEAR = "id:Clear text";
        SEARCH_RESULTS_LIST = "id:org.wikipedia:id/search_results_list";

    }


    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }


}
