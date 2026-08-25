package com.learningmate.screenplay.apps.demoqa.ui;

import com.learningmate.screenplay.core.ui.Target;
import org.openqa.selenium.By;

public class WebTableUi {

    public static final Target ADD_BUTTON = Target.the("Add Button")
            .located(By.id("addNewRecordButton"));

    public static final Target FIRST_NAME_FIELD = Target.the("First Name Field")
            .located(By.id("firstName"));

    public static final Target LAST_NAME_FIELD = Target.the("Last Name Field")
            .located(By.id("lastName"));

    public static final Target EMAIL_FIELD = Target.the("Email Field")
            .located(By.id("userEmail"));

    public static final Target AGE_FIELD = Target.the("Age Field")
            .located(By.id("age"));

    public static final Target SALARY_FIELD = Target.the("Salary Field")
            .located(By.id("salary"));

    public static final Target DEPARTMENT_FIELD = Target.the("Department Field")
            .located(By.id("department"));

    public static final Target SUBMIT_BUTTON = Target.the("Submit Button")
            .located(By.id("submit"));

    public static final Target SEARCH_BOX = Target.the("Search Box")
            .located(By.id("searchBox"));

    public static final Target TABLE_ROWS = Target.the("Web Table Rows")
            .located(By.cssSelector("table tbody tr"));
}