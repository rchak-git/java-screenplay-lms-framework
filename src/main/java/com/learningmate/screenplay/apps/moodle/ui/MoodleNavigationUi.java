package com.learningmate.screenplay.apps.moodle.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * MoodleNavigationUi provides Target locators for the top navigation bar
 * and user dropdown menu.
 */
public class MoodleNavigationUi {

    public static final Target USER_MENU_TOGGLE = Target.the("User menu toggle button")
            .located(By.cssSelector("#user-menu-toggle, .userbutton"));

    // Parameterized Target accepting option name at runtime
    public static final Target MENU_OPTION_BY_NAME = Target.the("{0} menu option")
            .locatedBy("//div[contains(@class, 'dropdown-menu')]//a[contains(normalize-space(), '{0}')]");
}