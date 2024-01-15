package org.example.steps;

import io.cucumber.java.ru.*;
import org.example.managers.PageManager;

import java.util.Map;


public class ItemsSteps {

    private PageManager app = PageManager.getPageManager();
    private String nameField;

    @Если("нажать на элемент выпадающий список в меню")
    public void clickOnItemInMenu() {
        app.getHomePage().selectNavbarDropdown();
    }

    @И("выбрать в выпадающем списке элемент {string}")
    public void selectItemFromList(String string) {
        app.getHomePage().selectSubMenu(string);
    }

    @То("открылась страница с товарами")
    public void foodPageOpened() {
        app.getFoodPage().checkOpenFoodPage();
    }

    @Тогда("проверить заголовки таблицы")
    public void checkTableHeaders() {
        app.getFoodPage().checkFoodTableHeader();
    }

    @И("проверить цвет кнопки Добавить")
    public void checkColorOfAddButton() {
        app.getFoodPage().checkButtonColor();
    }

    @Если("нажать на кнопку Добавить")
    public void clickOnAddButton() {
        app.getFoodPage().clickButtonAdd();
    }

    @Тогда("проверить отображение полей формы")
    public void checkDisplayOfLabels() {
        app.getAddingPage().checkLabels();
    }

    @И("проверить значения выпадающего списка")
    public void checkValuesOfList() {
        app.getAddingPage().checkSelectType();
    }

    @То("закрыть окно")
    public void closePage() {
        app.getAddingPage().clickBtnClose();
    }

    @Когда("заполняются поля:")
    public void fillFields(Map<String, String> dataTable) {
        for (var entry : dataTable.entrySet()) {
            app.getAddingPage().fillField(entry.getKey(), entry.getValue());
        }
    }

    @И("чекбокс Экзотический принимает значение {string}")
    public void clickOnExoticCheckbox(String exotic) {
        app.getAddingPage().clickCheckbox(Boolean.valueOf(exotic));
    }

    @Также("нажать на кнопку Сохранить")
    public void clickSaveButton() {
        app.getAddingPage().clickBtnSave();
    }

    @Тогда("проверить отображение элемента {string}, {string}, {string} в таблицу")
    public void checkDisplayElementInTable(String name, String type, String exotic) {
        app.getFoodPage().checkItemInTable(name, type, Boolean.valueOf(exotic));
    }

    @И("удалить элемент")
    public void deleteItem() {
        app.getFoodPage().deleteItems();
    }

    @Если("не заполненить поле {string}")
    public void checkErrorDisplay(String nameField) {
        this.nameField = nameField;
    }

    @То("выводится сообщение {string}")
    public void displayMessage(String message) {
        app.getAddingPage().checkErrorMessageAtField(nameField, message);
    }
}
