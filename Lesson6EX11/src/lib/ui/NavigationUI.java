package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {

protected static String
    MY_LIST_LINK,
    BUTTON_TEXT_TPL,
    OPEN_NAVIGATION;

    public NavigationUI (AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getTitleButton(String name_of_button)
    {
        return BUTTON_TEXT_TPL.replace("{BUTTON_NAME}", name_of_button);
    }
    /*TEMPLATES METHODS*/


    public void clickMyLists()
    {
        this.waitForElementAndClick(
                MY_LIST_LINK,
                "Cannot find navigation button to My list",
                5
        );
    }

    public void clickButtonUseText(String name_of_button)
    {
        String button = getTitleButton(name_of_button);
        this.waitForElementAndClick(button, "Cannot find button: " + name_of_button, 10);
    }


}
