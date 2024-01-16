package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.managers.DriverManager;
import org.example.managers.InitManager;
import org.example.managers.PageManager;
import org.example.managers.TestPropManager;

import static org.example.utils.PropConst.BASE_URL;

public class Hooks {

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    @Before
    public void init() {
        InitManager.initFramework();
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    @After
    public void quit() {
        InitManager.quitFramework();
        PageManager.setPageManagerNull();
    }

}
