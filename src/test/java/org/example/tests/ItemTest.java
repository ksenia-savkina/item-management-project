package org.example.tests;

import org.example.basetestsclass.BaseTests;
import org.junit.jupiter.api.Test;

public class ItemTest extends BaseTests {

    @Test
    void testAddingItem() {
        app.getHomePage()
                .selectNavbarDropdown()
                .selectSubMenu("Товары")
                .checkFoodTableHeader()
                .checkButtonColor()

                .clickButtonAdd()
                .checkLabels()
                .checkSelectType()
                .fillField("Наименование", "абв")
                .fillField("Тип", "VEGETABLE")
                .clickCheckbox()
                .clickBtnSave()
                .checkItemInTable("абв", "Овощ", "true")

                .clickButtonAdd()
                .fillField("Наименование", "1")
                .fillField("Тип", "FRUIT")
                .clickBtnSave()
                .checkItemInTable("1", "Фрукт", "false")

                .clickButtonAdd()
                .fillField("Наименование", "*?!")
                .fillField("Тип", "VEGETABLE")
                .clickBtnSave()
                .checkItemInTable("*?!", "Овощ", "false")

                .clickButtonAdd()
                .fillField("Наименование", "аб*в12?!3")
                .fillField("Тип", "FRUIT")
                .clickCheckbox()
                .clickBtnSave()
                .checkItemInTable("аб*в12?!3", "Фрукт", "true")

                .clickButtonAdd()
                .checkErrorMessageAtField("Наименование", "Поле Наименование не заполнено / заполнено неправильно!")
                .clickBtnSave();
    }
}
