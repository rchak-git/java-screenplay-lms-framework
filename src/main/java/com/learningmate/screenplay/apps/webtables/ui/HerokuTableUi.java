package com.learningmate.screenplay.apps.webtables.ui;

import com.learningmate.screenplay.core.ui.Target;
import org.openqa.selenium.By;

public class HerokuTableUi {

    // Table 1 Root Container
    public static final Target TABLE_1 = Target.the("Heroku Table 1")
            .located(By.xpath("//table[@id='table1']"));

    public static final Target ALL_HEADERS = Target.the("Heroku Table 1 Headers")
            .located(By.xpath("//table[@id='table1']//thead//th"));
    // Dynamic Target: {0} = Row Key (e.g., 'Bach'), {1} = Column Index (e.g., 3)
    public static final Target CELL_BY_KEY_AND_COL = Target.the("Cell for %s at column %s")
            .located(By.xpath("//table[@id='table1']//tr[td[text()='{0}']]/td[{1}]"));

    // Dynamic Target: {0} = Row Key, {1} = Action Name ('edit' or 'delete')
    public static final Target ROW_ACTION_LINK = Target.the("Action link %s for row %s")
            .located(By.xpath("//table[@id='table1']//tr[td[text()='{0}']]//a[text()='{1}']"));
}