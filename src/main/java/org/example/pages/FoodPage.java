package org.example.pages;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FoodPage extends BasePage {

    @FindBy(xpath = "//h5")
    private WebElement tableFoodTitle;

    @FindBy(xpath = "//thead//th")
    private List<WebElement> tableFoodHeader;

    @FindBy(xpath = "//button[.='Добавить']")
    private WebElement btnAdd;

    @FindBy(xpath = "//table/tbody/tr")
    private List<WebElement> tableItems;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return FoodPage - т.е. остаемся на этой странице
     */
    public FoodPage checkOpenFoodPage() {
        Assertions.assertEquals("Список товаров", tableFoodTitle.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    /**
     * Проверка отображения таблицы, путём проверки Header таблицы
     *
     * @return FoodPage - т.е. остаемся на этой странице
     */
    public FoodPage checkFoodTableHeader() {
        List<String> expectedHeader = Arrays.asList("#", "Наименование", "Тип", "Экзотический");

        ArrayList<String> actualHeader = tableFoodHeader
                .stream()
                .map(head -> waitUtilElementToBeVisible(head).getText())
                .collect(Collectors.toCollection(ArrayList::new));

        Assertions.assertEquals(expectedHeader, actualHeader, "Поля таблицы не соответствует требуемым");

        return this;
    }

    /**
     * Проверка цвета кнопки, путём проверки кода цвета кнопки
     *
     * @return FoodPage - т.е. остаемся на этой странице
     */
    public FoodPage checkButtonColor() {
        Assertions.assertEquals("rgba(0, 123, 255, 1)",
                waitUtilElementToBeClickable(btnAdd).getCssValue("background-color"),
                "Цвет кнопки не соответствует требуемому");
        return this;
    }

    /**
     * Клик по кнопке "Добавить"
     *
     * @return AddingPage - т.е. переходим на страницу {@link AddingPage}
     */
    public AddingPage clickButtonAdd() {
        waitUtilElementToBeClickable(btnAdd).click();
        return pageManager.getAddingPage().checkOpenAddingPage();
    }

    /**
     * Проверка отображения вставленного элемента в таблицу, путём проверки последнего элемента таблицы
     *
     * @return FoodPage - т.е. остаемся на этой странице
     */
    public FoodPage checkItemInTable(String name, String exotic, String type) {
        tableItems.forEach(this::waitUtilElementToBeVisible);
        ArrayList<String> lastItemValues = tableItems
                .get(tableItems.size() - 1)
                .findElements(By.tagName("td"))
                .stream()
                .map(element -> waitUtilElementToBeVisible(element).getText())
                .collect(Collectors.toCollection(ArrayList::new));

        List<String> expectedValues = Arrays.asList(name, exotic, type);
        Assertions.assertEquals(expectedValues, lastItemValues, "Товар добавлен с неверными значениями");

        return this;
    }
}
