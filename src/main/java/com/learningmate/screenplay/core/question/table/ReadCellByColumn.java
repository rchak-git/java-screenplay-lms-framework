package com.learningmate.screenplay.core.question.table;

import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.question.Question;
import com.learningmate.screenplay.core.question.TextOf;
import com.learningmate.screenplay.core.ui.Target;
import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ReadCellByColumn implements Question<String> {

    private final Target tableHeaderTarget; // Generic header collection target
    private final Target cellTargetTemplate; // Generic row/col cell target
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
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // 1. Wait for table headers to be present and populated
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tableHeaderTarget.getLocator()));

        List<WebElement> headers = driver.findElements(tableHeaderTarget.getLocator());

        int columnIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().trim().equalsIgnoreCase(columnName)) {
                columnIndex = i + 1; // 1-indexed for XPath
                break;
            }
        }

        if (columnIndex == -1) {
            throw new RuntimeException("Column header '" + columnName + "' not found in table! Found headers: "
                    + headers.stream().map(e -> "'" + e.getText().trim() + "'").toList());
        }

        // 2. Resolve cell target template dynamically
        Target resolvedCellTarget = cellTargetTemplate.of(rowKey, columnIndex);

        // Delegate to standard TextOf question
        return TextOf.field(resolvedCellTarget).answeredBy(actor);
    }
}