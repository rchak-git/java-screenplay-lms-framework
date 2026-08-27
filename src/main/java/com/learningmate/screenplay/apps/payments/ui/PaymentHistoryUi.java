package com.learningmate.screenplay.apps.payments.ui;


import com.learningmate.screenplay.core.ui.Target;
import org.openqa.selenium.By;

public class PaymentHistoryUi {

    public static final Target PAYMENT_HISTORY_TITLE = Target.the("Payment History title")
            .located(By.cssSelector("h2"));

    public static final Target OPEN_DEMO_LINK = Target.the("Open Demo Table link")
            .located(By.cssSelector(".demo-link a"));

    public static final Target PAYMENT_HISTORY_TABLE = Target.the("Payment History table")
            .located(By.cssSelector("table"));

    public static final Target TABLE_HEADERS = Target.the("Payment History table headers")
            .located(By.cssSelector("table thead th"));

    public static final Target TABLE_ROWS = Target.the("Payment History table rows")
            .located(By.cssSelector("table tbody tr"));

    public static final Target ROW_BY_CUSTOMER_NAME = Target.the("Payment row for %s")
            .locatedBy("//table//tr[td[2][normalize-space()='{0}']]");

    public static final Target ROW_BY_CUSTOMER_NAME_AND_AMOUNT = Target.the("Payment row for %s and %s")
            .locatedBy("//table//tr[td[2][normalize-space()='{0}'] and td[3][normalize-space()='{1}']]");

    public static final Target CELL_BY_CUSTOMER_NAME_AND_COLUMN = Target.the("Payment cell for %s at column %s")
            .locatedBy("//table//tr[td[2][normalize-space()='{0}']]/td[{1}]");
}