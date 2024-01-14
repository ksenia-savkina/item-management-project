package org.example.tests;

import org.example.basetestsclass.BaseTests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ItemTest extends BaseTests {

    @Test
    public void testFoodPageDisplay() {
        app.getHomePage()
                .selectNavbarDropdown()
                .selectSubMenu("Товары")
                .checkFoodTableHeader()
                .checkButtonColor();
    }


    @Test
    public void testAddingPageDisplay() {
        app.getHomePage()
                .selectNavbarDropdown()
                .selectSubMenu("Товары")
                .clickButtonAdd()
                .checkLabels()
                .checkSelectType()
                .clickBtnClose();
    }


    @ParameterizedTest
    @CsvSource(value = {
            "абв, VEGETABLE, Овощ, true",
            "1, FRUIT, Фрукт, false",
            "*?!, VEGETABLE, Овощ, false",
            "аб*в12?!3, FRUIT, Фрукт, true"
    })
    public void testAddingItemPositive(String name, String typeValue, String typeString, Boolean isExotic) {
        app.getHomePage()
                .selectNavbarDropdown()
                .selectSubMenu("Товары")
                .clickButtonAdd()
                .fillField("Наименование", name)
                .fillField("Тип", typeValue)
                .clickCheckbox(isExotic)
                .clickBtnSave()
                .checkItemInTable(name, typeString, isExotic)
                .deleteItems();
    }


    @Test
    public void testAddingItemNegative() {
        app.getHomePage()
                .selectNavbarDropdown()
                .selectSubMenu("Товары")
                .clickButtonAdd()
                .checkErrorMessageAtField("Наименование", "Поле Наименование не заполнено / заполнено неправильно!");
    }
}
