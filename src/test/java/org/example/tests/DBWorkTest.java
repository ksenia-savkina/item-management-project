package org.example.tests;

import org.example.basetestsclass.BaseTests;
import org.example.database.ItemTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@DisplayName("DBWorkTest - класс, проверяющий функциональность добавления товаров в БД")
public class DBWorkTest extends BaseTests {

    private final ItemTable itemTable =  jdbcManager.getItemTable();

    @DisplayName("Тест с добавлением товара, проверкой товара в таблице, удалением товара")
    @ParameterizedTest
    @CsvSource(value = {
            "абв, VEGETABLE, 1",
            "1, FRUIT, 0",
            "*?!, VEGETABLE, 0",
            "аб*в12?!3, FRUIT, 1"
    })
    public void addingTest(String name, String type, int exotic) {
        itemTable.insertItem(name, type, exotic);
        itemTable.readItem(name, type, exotic);
        itemTable.deleteItem(name, type, exotic);
    }
}
