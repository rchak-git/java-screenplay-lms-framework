package com.learningmate.screenplay.tests.webtables;


import com.learningmate.screenplay.apps.webtables.ui.HerokuTableUi;
import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.action.table.ClickRowAction;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.question.table.ReadCellByColumn;
import com.learningmate.screenplay.core.question.table.ReadTableCell;
import com.learningmate.screenplay.core.ui.Target;
import com.learningmate.screenplay.core.util.DataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;

public class WebTableTest {

    private WebDriver driver;
    private Actor rajib;

    @BeforeMethod
    public void setUp() {
        // Initialize Driver (adjust driver setup if using WebDriverManager / DriverFactory)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/tables");

        // Initialize Screenplay Actor
        rajib = Actor.named("Rajib");
        rajib.can(BrowseTheWeb.with(driver)); // Uses your BrowseTheWeb ability
    }

    @Test
    public void verifyUserEmailUsingDataReader() {
        // 1. Fetch test data map using core DataReader
        Map<String, String> userRecord = DataReader.getRecord("testdata/herokuapp_table_data.yaml", "users.bach");

        String lastName = userRecord.get("lastName");
        String expectedEmail = userRecord.get("email");
        int emailColumnIndex = 3;

        // 2. Resolve dynamic Target (.of() formats both XPath & human description)
        Target targetCell = HerokuTableUi.CELL_BY_KEY_AND_COL.of(lastName, emailColumnIndex);

        // 3. Ask Screenplay Question to extract text from cell
        String actualEmail = ReadTableCell.valueOf(targetCell).answeredBy(rajib);

        // 4. Validate output
        Assert.assertEquals(actualEmail, expectedEmail,
                "Displayed email in web table does not match expected value from YAML!");
    }

    @Test
    public void verifyUserClickEditLink() {
        // 1. Fetch test data map using core DataReader
        Map<String, String> userRecord = DataReader.getRecord("testdata/herokuapp_table_data.yaml", "users.bach");

        String lastName = userRecord.get("lastName");
         String actionValue = "edit";

        // 2. Resolve dynamic Target (.of() formats both XPath & human description)
        Target targetCell = HerokuTableUi.ROW_ACTION_LINK.of(lastName, actionValue);

        // 3. Ask Screenplay Table Task  to click the edit link in the row

       rajib.attemptsTo(
               ClickRowAction.on(targetCell)
               );
        Assert.assertTrue(driver.getCurrentUrl().endsWith("#edit"),
                "URL did not update with #edit hash after clicking action link!");
         }


    @Test
    public void verifyDynamicMultiColumnQuery() {
        String rowKey = "Bach";
        String columnName = "Email";

        // Ask the Screenplay Question
        String actualEmail = ReadCellByColumn.from(
                HerokuTableUi.ALL_HEADERS,
                HerokuTableUi.CELL_BY_KEY_AND_COL,
                rowKey,
                columnName
        ).answeredBy(rajib);

        Assert.assertEquals(actualEmail, "fbach@yahoo.com", "Extracted email did not match!");
    }

    @Test
    public void verifyDynamicMultiColumnQueryDataDriven() {
        // 1. Load test data from YAML
        Map<String, String> userRecord = DataReader.getRecord("testdata/herokuapp_table_data.yaml", "users.bach");

        String rowKey = userRecord.get("lastName"); // "Bach"
        String columnName = "Email";
        String expectedEmail = userRecord.get("email"); // "fbach@yahoo.com"

        // 2. Query web table dynamically
        String actualEmail = ReadCellByColumn.from(
                HerokuTableUi.ALL_HEADERS,
                HerokuTableUi.CELL_BY_KEY_AND_COL,
                rowKey,
                columnName
        ).answeredBy(rajib);

        // 3. Assert against YAML ground truth
        Assert.assertEquals(actualEmail, expectedEmail, "Extracted email did not match YAML dataset!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}