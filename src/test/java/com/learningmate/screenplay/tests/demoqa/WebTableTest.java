package com.learningmate.screenplay.tests.demoqa;

import com.learningmate.screenplay.apps.demoqa.question.TableRows;
import com.learningmate.screenplay.apps.demoqa.task.AddUserRecord;
import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.action.OpenUrl;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.util.DataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebTableTest {

    private WebDriver driver;
    private Actor tester;
    private static final String DATA_FILE = "demoqa/webtables_data.yaml";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        tester = Actor.named("Rajib").can(BrowseTheWeb.with(driver));
    }

    @Test
    public void testAddUserFromYamlData() {
        String dataId = "USER_001";
        String expectedEmail = DataReader.get(DATA_FILE, "webtable_data." + dataId + ".email");

        // 1. Navigate to Web Tables page
        tester.attemptsTo(
                OpenUrl.to("https://demoqa.com/webtables")
        );

        // 2. Perform business task using YAML data ID
        tester.attemptsTo(
                // 3. Query state and assert
                AddUserRecord.usingFile(DATA_FILE,  dataId)

        );

        boolean isUserFound = tester.asks(TableRows.containsText(expectedEmail));
        Assert.assertTrue(isUserFound, "Expected user record with email " + expectedEmail + " was not found in table!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}