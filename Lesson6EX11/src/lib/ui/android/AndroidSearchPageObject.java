package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject
{
 static {
             SEARCH_INIT_ELEMENT = "xpath://*[@text='Search Wikipedia']";
             SEARCH_INPUT = "xpath://*[@text='Search…']";
             SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
             SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]";
             SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
             SEARCH_EMPTY_RESULT_ELEMENT =  "xpath://*[@text='No results found']";
             SEARCH_RESULT_BY_ID = "id:org.wikipedia:id/page_list_item_title";
             SEARCH_BY_TITLE_OR_DESCRIPTION = "xpath://*[@class = 'android.widget.LinearLayout']//*[@text = '{TITLE_OR_DESCRIPTION}']";
             SEARCH_RESULTS_ELEMENTS = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@class,'android.widget.LinearLayout')]";
             SEARCH_RESULTS_LIST = "id:org.wikipedia:id/search_results_list";
             OPTIONS_CHANGE_LANGUAGE = "xpath://*[@text='Change language']";


 }


    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

}
