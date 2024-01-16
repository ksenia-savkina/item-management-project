package org.example.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(id = "navbarDropdown")
    private WebElement btnNavbarDropdown;

    @FindBy(xpath = "//a[@class='dropdown-item']")
    private List<WebElement> listDropdownMenu;

    /**
     * Функция клика на Dropdown
     *
     * @return HomePage  - т.е. переходим на страницу {@link HomePage}
     */
    @Step("Кликаем по NavbarDropdown")
    public HomePage selectNavbarDropdown() {
        waitUtilElementToBeClickable(btnNavbarDropdown).click();
        return this;
    }

    /**
     * Функция клика на любое подменю
     *
     * @param nameDropdownMenu - наименование подменю
     * @return FoodPage - т.е. переходим на страницу {@link FoodPage}
     */
    @Step("Кликаем на подменю {nameDropdownMenu}")
    public FoodPage selectSubMenu(String nameDropdownMenu) {
        for (WebElement menuItem : listDropdownMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameDropdownMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return pageManager.getFoodPage().checkOpenFoodPage();
            }
        }
        Assertions.fail("Подменю '" + nameDropdownMenu + "' не было найдено на стартовой странице!");
        return pageManager.getFoodPage();
    }
}