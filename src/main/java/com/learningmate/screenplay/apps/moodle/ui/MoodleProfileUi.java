package com.learningmate.screenplay.apps.moodle.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * MoodleProfileUi provides Target locators for elements on the User Profile page.
 */
public class MoodleProfileUi {

    // Main Page Heading (e.g., "Student User")
    public static final Target PROFILE_NAME_HEADING = Target.the("Profile name heading")
            .located(By.cssSelector(".page-header-headings h1, .userheader h1"));

    // User Profile Details Cards
    public static final Target TIMEZONE_TEXT = Target.the("User timezone text")
            .located(By.xpath("//dt[text()='Timezone']/following-sibling::dd"));

    public static final Target COURSE_PROFILES_LINK = Target.the("Course profiles link")
            .located(By.xpath("//section[contains(@class,'node_category')]//a[contains(@href, 'course/view.php')]"));

    // Explicit static locator for edge-case field with complex DOM wrapper
    public static final Target EMAIL_ADDRESS = Target.the("Email address profile field")
            .locatedBy("//dt[contains(normalize-space(), 'Email address')]/following-sibling::dd[1]//a");
    public static final Target PROFILE_FIELD = Target.the("{0} profile field")
            .locatedBy("//dt[contains(normalize-space(), '{0}')]/following-sibling::dd[1]");
    public static final Target EDIT_PROFILE_LINK = Target.the("Edit profile link")
            .located(By.xpath("//a[contains(@href,'/user/edit.php') and contains(normalize-space(),'Edit profile')]"));

}