package com.learningmate.screenplay.apps.moodle.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
public class MoodleEditProfileUi {
    private MoodleEditProfileUi() {}

    public static final Target CITY_TOWN_INPUT = Target.the("City/town input field")
            .located(By.id("id_city"));

    public static final Target COUNTRY_SELECT = Target.the("Country select field")
            .located(By.id("id_country"));

    public static final Target UPDATE_PROFILE_BUTTON = Target.the("Update profile button")
            .located(By.id("id_submitbutton"));
}
