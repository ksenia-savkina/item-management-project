package org.example.tests;

import org.example.basetestsclass.BaseTests;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DBWorkTest extends BaseTests {

    @ParameterizedTest
    @CsvSource(value = {
            "абв, VEGETABLE, 1",
            "1, FRUIT, 0",
            "*?!, VEGETABLE, 0",
            "аб*в12?!3, FRUIT, 1"
    })
    public void Test(String name, String type, int exotic) {
        jdbcManager.insertItem(name, type, exotic);
        jdbcManager.readItem(name, type, exotic);
        jdbcManager.deleteItem(name, type, exotic);
    }
}
