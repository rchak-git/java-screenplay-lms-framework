package com.learningmate.screenplay.apps.payments.question;

import com.learningmate.screenplay.apps.payments.ui.PaymentHistoryUi;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.question.Question;
import com.learningmate.screenplay.core.question.table.ReadCellByColumn;
import com.learningmate.screenplay.core.ui.Target;

public class PaymentHistoryQuestions implements Question<String> {

    private final String customerName;
    private final String columnName;

    private final Target tableHeader = PaymentHistoryUi.TABLE_HEADERS;
    private final Target cellByCustomerAndColumn = PaymentHistoryUi.CELL_BY_CUSTOMER_NAME_AND_COLUMN;

    private PaymentHistoryQuestions (String customerName, String columnName) {
        this.customerName = customerName;
        this.columnName = columnName;
    }

    public static Question<String> cellValue(String customerName, String columnName)
    {

        return new PaymentHistoryQuestions(customerName,columnName);


    }

    @Override
    public String answeredBy(Actor actor) {

      return   ReadCellByColumn.from(tableHeader,cellByCustomerAndColumn,customerName,columnName).answeredBy(actor);
    }
}
