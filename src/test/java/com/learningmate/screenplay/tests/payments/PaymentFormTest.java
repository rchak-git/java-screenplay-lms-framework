package com.learningmate.screenplay.tests.payments;

import com.learningmate.screenplay.apps.payments.question.PaymentHistoryQuestions;
import com.learningmate.screenplay.apps.payments.task.CreatePayment;
import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.action.OpenUrl;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.util.DataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class PaymentFormTest {

    private static final String YAML_PATH = "payments/payment_form_data.yaml";
    private Actor actor;
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        actor = Actor.named("Rajib").can(BrowseTheWeb.with(driver));

        actor.attemptsTo(
                OpenUrl.to("http://localhost:8080/payment-form")
        );
    }

    @Test
    public void verifyValidPaymentSubmissionAndHistoryRecord() {
        verifyPaymentFlowForScenario("VALID_PAYMENT");
    }

    @Test
    public void verifyPendingPaymentSubmissionAndHistoryRecord() {
        verifyPaymentFlowForScenario("PENDING_PAYMENT");
    }

    @Test
    public void verifyHighValuePaymentSubmissionAndHistoryRecord() {
        verifyPaymentFlowForScenario("HIGH_VALUE_PAYMENT");
    }

    /**
     * Reusable verification helper combining submission + dynamic history table assertions
     */
    private void verifyPaymentFlowForScenario(String dataId) {
        // 1. Load Test Data
        Map<String, String> testData = DataReader.getRecord(YAML_PATH, dataId);
        String customerName = testData.get("customerName");
        String inputAmount = testData.get("amount");
        String expectedStatus = testData.get("scenario");
        String expectedAmount =   inputAmount;   //inputAmount.startsWith("$") ? inputAmount : "$" + inputAmount;

        // 2. Perform Action (Submit Payment)
        actor.attemptsTo(CreatePayment.withData(testData));

        // 3. Query Payment History Table
        String actualCustomer = actor.asks(PaymentHistoryQuestions.cellValue(customerName, "Customer Name"));
        String actualAmount   = actor.asks(PaymentHistoryQuestions.cellValue(customerName, "Amount"));
        String actualStatus   = actor.asks(PaymentHistoryQuestions.cellValue(customerName, "Status"));
        String actualId       = actor.asks(PaymentHistoryQuestions.cellValue(customerName, "Payment ID"));
        String actualCreatedAt = actor.asks(PaymentHistoryQuestions.cellValue(customerName, "Created At"));

        // 4. Multi-Field Soft Assertions
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualCustomer, customerName, "Customer Name mismatch!");
        softAssert.assertEquals(actualAmount, expectedAmount, "Amount mismatch!");
        softAssert.assertEquals(actualStatus, expectedStatus, "Status mismatch!");
        softAssert.assertFalse(actualId == null || actualId.trim().isEmpty(), "Payment ID should not be empty!");
        softAssert.assertFalse(actualCreatedAt == null || actualCreatedAt.trim().isEmpty(), "Created At should not be empty!");

        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}