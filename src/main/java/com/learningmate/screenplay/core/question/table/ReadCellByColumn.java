package com.learningmate.screenplay.core.question.table;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;

public class ReadCellByColumn implements Question<String> {

    private final Target tableHeaderTarget;
    private final Target cellTargetTemplate;
    private final String rowKey;
    private final String columnName;

    public ReadCellByColumn(Target tableHeaderTarget, Target cellTargetTemplate, String rowKey, String columnName) {
        this.tableHeaderTarget = tableHeaderTarget;
        this.cellTargetTemplate = cellTargetTemplate;
        this.rowKey = rowKey;
        this.columnName = columnName;
    }

    public static ReadCellByColumn from(Target tableHeaderTarget, Target cellTargetTemplate, String rowKey, String columnName) {
        return new ReadCellByColumn(tableHeaderTarget, cellTargetTemplate, rowKey, columnName);
    }

    @Override
    public String answeredBy(Actor actor) {
        // 1. Resolve header elements directly via Serenity's target engine
        List<WebElementFacade> headers = tableHeaderTarget.resolveAllFor(actor);

        // 2. Find column index (1-based for XPath)
        int columnIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(columnName)) {
                columnIndex = i + 1;
                break;
            }
        }

        if (columnIndex == -1) {
            List<String> foundHeaderNames = headers.stream().map(e -> "'" + e.getText().trim() + "'").toList();
            throw new AssertionError("Column header '" + columnName + "' not found in table! Found headers: " + foundHeaderNames);
        }

        // 3. Resolve parameterized target and ask native Serenity Text question
        Target resolvedCellTarget = cellTargetTemplate.of(rowKey, String.valueOf(columnIndex));
        return Text.of(resolvedCellTarget).answeredBy(actor);
    }
}