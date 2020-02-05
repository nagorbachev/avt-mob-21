package tests;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import lib.ui.factories.SearchPageObjectFactory;
import lib.Platform;

public class EX12 extends CoreTestCase {

    @Test
    public void testCheckSearchWord() {

        String searchedField= "hr";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchedField);

        String[] search_topics;

        if(Platform.getInstance().isAndroid()) {
            search_topics = SearchPageObject.getAllTopicsInIosSearch();
        }
        else {
            search_topics = SearchPageObject.getAllTopicsInIosSearch();
        }

        for (int i=0; i<search_topics.length-1; i++){
            boolean result_searched_word =  search_topics[i].contains(searchedField);
            assertTrue("Topic without searched word!", result_searched_word);
        }
    }


}
