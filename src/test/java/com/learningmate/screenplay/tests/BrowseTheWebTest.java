package com.learningmate.screenplay.tests;

import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.actor.Actor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowseTheWebTest {

    @Test
    public void testBrowseTheWebAbility() {
        // Setup Chrome Driver (Selenium 4 automatically manages drivers)
        WebDriver driver = new ChromeDriver();

        Actor instructor = Actor.named("Rajib")
                .can(BrowseTheWeb.with(driver));

        // Extract ability and verify driver is attached
        WebDriver retrievedDriver = BrowseTheWeb.as(instructor).getDriver();
        Assert.assertNotNull(retrievedDriver);

        driver.quit();
    }
}