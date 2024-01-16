package org.example.managers;

import org.example.database.ItemTable;
import org.example.pages.FoodPage;
import org.junit.jupiter.api.Assertions;

import java.sql.DriverManager;
import java.sql.*;

import static org.example.utils.PropConst.*;

public class JDBCManager {

    private static JDBCManager jdbcManager;

    private static Connection connection;

    private ItemTable itemTable;

    /**
     * Ленивая инициализация JDBCManager
     *
     * @return JDBCManager
     */
    public static JDBCManager getJDBCManager() {
        if (jdbcManager == null) {
            jdbcManager = new JDBCManager();
        }
        return jdbcManager;
    }

    /**
     * Метод ленивой инициализации Connection
     *
     * @return Connection - возвращает Connection
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                TestPropManager testPropManager = TestPropManager.getTestPropManager();
                String url = testPropManager.getProperty(JDBC_URL);
                String user = testPropManager.getProperty(JDBC_USER);
                String password = testPropManager.getProperty(JDBC_PASSWORD);
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    /**
     * Метод закрытия соединения
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ленивая инициализация {@link ItemTable}
     *
     * @return ItemTable
     */
    public ItemTable getItemTable() {
        if (itemTable == null) {
            itemTable = new ItemTable();
        }
        return itemTable;
    }
}
