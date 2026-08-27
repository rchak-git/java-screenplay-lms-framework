package com.learningmate.screenplay.apps.payments.ui;



import com.learningmate.screenplay.core.ui.Target;
import org.openqa.selenium.By;

public class PaymentFormUi {

    public static final Target CUSTOMER_NAME = Target.the("Customer Name Field")
            .located(By.xpath("//input[@id='customerName']"));

    public static final Target AMOUNT = Target.the("Amount Field")
            .located(By.xpath("//input[@id='amount']"));

    public static final Target SCENARIO = Target.the("Scenario Dropdown")
            .located(By.xpath("//select[@id='scenario']"));

    public static final Target SUBMIT_BUTTON = Target.the("Submit Button")
            .located(By.xpath("//button[@type='submit']"));
}