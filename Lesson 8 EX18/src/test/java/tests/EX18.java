import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class EX18 extends CoreTestCase {

    @Test
    public void testCheckSearchWord () {

        String searchedField= "qa";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchedField);

        String[] search_topics;
        if(Platform.getInstance().isAndroid()) {
            search_topics = SearchPageObject.getAllTopicsAtSearchResults();
        }
        else if (Platform.getInstance().isIOS()) {
            search_topics = SearchPageObject.getAllTopicsInIosSearch();
        } else {
            search_topics = SearchPageObject.getAllTopicsAtSearchResults();
        }

        for (int i=0; i<search_topics.length-1; i++){
            boolean result_searched_word =  search_topics[i].contains(searchedField);
            assertTrue("Topic is not contained searched word", result_searched_word);
        }
    }

}
