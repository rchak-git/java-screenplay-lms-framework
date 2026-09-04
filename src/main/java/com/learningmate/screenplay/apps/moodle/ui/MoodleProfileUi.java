package com.learningmate.screenplay.apps.moodle.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * MoodleProfileUi provides Target locators for elements on the User Profile page.
 */


import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class MoodleProfileUi {

    // Main Profile Header (displays full name, e.g., "Rajib Automation")
    public static final Target PROFILE_NAME_HEADING = Target.the("Profile name heading")
            .located(By.cssSelector("div.d-flex h2, .page-header-headings h1, .userheader h1"));

    // User Profile Details Cards
    public static final Target EMAIL_ADDRESS = Target.the("Email address profile field")
            .located(By.xpath("//dt[contains(normalize-space(), 'Email address')]/following-sibling::dd[1]//a"));

    public static final Target TIMEZONE_TEXT = Target.the("User timezone text")
            .located(By.xpath("//dt[text()='Timezone']/following-sibling::dd"));

    public static final Target COURSE_PROFILES_LINK = Target.the("Course profiles link")
            .located(By.xpath("//section[contains(@class,'node_category')]//a[contains(@href, 'course/view.php')]"));

    public static final Target EDIT_PROFILE_LINK = Target.the("Edit profile link")
            .located(By.xpath("//a[contains(@href,'/user/edit.php') and contains(normalize-space(),'Edit profile')]"));
}