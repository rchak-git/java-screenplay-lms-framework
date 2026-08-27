package com.learningmate.screenplay.apps.payments.task;


import com.learningmate.screenplay.apps.payments.ui.PaymentFormUi;
import com.learningmate.screenplay.core.action.Click;
import com.learningmate.screenplay.core.action.EnterText;
import com.learningmate.screenplay.core.action.Performable;

import com.learningmate.screenplay.core.action.dropdown.SelectFromOptions;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.util.DataReader;

import java.util.Map;

public class CreatePayment implements Performable {

    private final String filePath;
    private final String dataId;
    private final String customerName;
    private final String amount;
    private final String scenario;

    // Constructor for YAML data loading
    private CreatePayment(String filePath, String dataId) {
        this.filePath = filePath;
        this.dataId = dataId;
        this.customerName = null;
        this.amount = null;
        this.scenario = null;
    }

    // Constructor for direct value passing
    private CreatePayment(String customerName, String amount, String scenario) {
        this.filePath = null;
        this.dataId = null;
        this.customerName = customerName;
        this.amount = amount;
        this.scenario = scenario;
    }

    public static CreatePayment usingFile(String filePath, String dataId) {
        return new CreatePayment(filePath, dataId);
    }

    public static CreatePayment withData(String customerName, String amount, String scenario) {
        return new CreatePayment(customerName, amount, scenario);
    }

    public static CreatePayment withData(Map<String, String> testData) {
        return new CreatePayment(
                testData.get("customerName"),
                testData.get("amount"),
                testData.get("scenario")
        );
    }

    @Override
    public void performAs(Actor actor) {
        String finalName = customerName;
        String finalAmount = amount;
        String finalScenario = scenario;

        // If configured to use YAML file, fetch record at runtime
        if (filePath != null && dataId != null) {
            Map<String, String> data = DataReader.getRecord(filePath, dataId);
            finalName = data.get("customerName");
            finalAmount = data.get("amount");
            finalScenario = data.get("scenario");
        }

        actor.attemptsTo(
                EnterText.into(PaymentFormUi.CUSTOMER_NAME).of(finalName),
                EnterText.into(PaymentFormUi.AMOUNT).of(finalAmount),
                SelectFromOptions.byVisibleText(finalScenario).from(PaymentFormUi.SCENARIO),
                Click.on(PaymentFormUi.SUBMIT_BUTTON)
        );
    }
}