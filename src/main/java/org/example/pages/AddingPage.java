package org.example.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddingPage extends BasePage {

    @FindBy(xpath = "//*[@id='editModal']")
    private WebElement editModal;

    @FindBy(xpath = "//*[@id='editModal']//h5[@id='editModalLabel']")
    private WebElement addingPageTitle;

    @FindBy(xpath = "//*[@id='editModal']//label")
    private List<WebElement> labels;

    @FindBy(xpath = "//select[@id='type']")
    private WebElement selectTypeElement;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement inputName;

    @FindBy(xpath = "//input[@id='exotic']")
    private WebElement checkboxExotic;

    @FindBy(xpath = "//button[@id='save']")
    private WebElement btnSave;

    @FindBy(xpath = "//button[@class='close']")
    private WebElement btnClose;


    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return AddingPage - т.е. остаемся на этой странице
     */
    public AddingPage checkOpenAddingPage() {
        waitUtilElementToBeVisible(editModal);
        Assertions.assertEquals("Добавление товара", addingPageTitle.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    /**
     * Проверка отображения полей формы, путём проверки labels на форме
     *
     * @return AddingPage - т.е. остаемся на этой странице
     */
    public AddingPage checkLabels() {
        List<String> expectedLabels = Arrays.asList("Наименование", "Тип", "Экзотический");

        ArrayList<String> actualLabels = labels
                .stream()
                .map(label -> waitUtilElementToBeVisible(label).getText())
                .collect(Collectors.toCollection(ArrayList::new));

        Assertions.assertEquals(expectedLabels, actualLabels, "Названия полей формы не соответствует требуемым");

        return this;
    }

    /**
     * Проверка значений выпадающего списка, путём проверки его значений
     *
     * @return AddingPage - т.е. остаемся на этой странице
     */
    public AddingPage checkSelectType() {
        Select selectType = new Select(waitUtilElementToBeClickable(selectTypeElement));

        ArrayList<String> actualOptions = selectType.getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toCollection(ArrayList::new));

        List<String> expectedOptions = Arrays.asList("Овощ", "Фрукт");

        Assertions.assertEquals(expectedOptions, actualOptions, "Значения выпадающего списка не соответствует требуемым");

        return this;
    }

    /**
     * Метод заполнения полей
     *
     * @param nameField - имя веб элемента, поля ввода
     * @param value     - значение вводимое в поле
     * @return AddingPage - т.е. остаемся на этой странице
     */
    public AddingPage fillField(String nameField, String value) {
        Select selectType = new Select(waitUtilElementToBeClickable(selectTypeElement));
        WebElement element = null;
        switch (nameField) {
            case "Наименование":
                waitUtilElementToBeVisible(inputName).click();
                inputName.sendKeys(value);
                element = inputName;
                break;
            case "Тип":
                waitUtilElementToBeVisible(selectTypeElement);
                selectType.selectByValue(value);
                element = selectTypeElement;
                break;
            default:
                Assertions.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Добавления товара'");
        }
        Assertions.assertEquals(value, element.getAttribute("value"), "Поле '" + nameField + "' было заполнено некорректно");
        return this;
    }

    /**
     * Клик по чекбоксу "Экзотический"
     *
     * @return AddingPage - т.е. остаемся на этой странице
     */
    public AddingPage clickCheckbox(Boolean isExotic) {
        if (isExotic)
            waitUtilElementToBeClickable(checkboxExotic).click();
        return this;
    }

    /**
     * Проверка ошибки относящаяся к конкретному полю на форме
     *
     * @param nameField  - имя веб элемента
     * @param errMassage - ошибка проверяемая которая отображается возле этого поля
     * @return AddingPage - т.е. остаемся на этой странице
     */
    public AddingPage checkErrorMessageAtField(String nameField, String errMassage) {
        WebElement element = null;
        switch (nameField) {
            case "Наименование":
                element = inputName;
                break;
            case "Тип":
                element = selectTypeElement;
                break;
            default:
                Assertions.fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Добавления товара'");
        }
        Assertions.assertEquals(errMassage, element.getText(), "Проверка ошибки у поля '" + nameField + "' была не пройдена");
        return this;
    }

    /**
     * Клик по кнопке "Сохранить"
     *
     * @return FoodPage - т.е. переходим на страницу {@link FoodPage}
     */
    public FoodPage clickBtnSave() {
        waitUtilElementToBeClickable(btnSave).click();
        waitForElementToDisappear(1000, 1);
        return pageManager.getFoodPage().checkOpenFoodPage();
    }

    /**
     * Клик по кнопке "Закрыть"
     *
     * @return FoodPage - т.е. переходим на страницу {@link FoodPage}
     */
    public FoodPage clickBtnClose() {
        waitUtilElementToBeClickable(btnClose).click();
        return pageManager.getFoodPage().checkOpenFoodPage();
    }
}
