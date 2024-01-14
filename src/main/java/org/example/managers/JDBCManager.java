package org.example.managers;

import org.junit.jupiter.api.Assertions;

import java.sql.DriverManager;
import java.sql.*;

public class JDBCManager {

    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb", "user", "pass");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод добавления товара
     *
     * @param name   - название товара
     * @param type   - тип товара
     * @param exotic - экзотический товар (1 - true, 0 - false)
     */
    public void insertItem(String name, String type, int exotic) {
        String queryInsert =
                "INSERT INTO food (food_name, food_type, food_exotic) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmtInsert = connection.prepareStatement(queryInsert);
            pstmtInsert.setString(1, name);
            pstmtInsert.setString(2, type);
            pstmtInsert.setInt(3, exotic);
            pstmtInsert.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод получения товара
     *
     * @param name   - название товара
     * @param type   - тип товара
     * @param exotic - экзотический товар (1 - true, 0 - false)
     */
    public void readItem(String name, String type, int exotic) {
        String querySelect = "SELECT * FROM food WHERE food_name = ? AND food_type = ? AND food_exotic = ?";
        try {
            PreparedStatement pstmtSelect = connection.prepareStatement(querySelect);
            pstmtSelect.setString(1, name);
            pstmtSelect.setString(2, type);
            pstmtSelect.setInt(3, exotic);

            ResultSet resultSet = pstmtSelect.executeQuery();
            resultSet.last();

            String resultName = resultSet.getString("food_name");
            String resultType = resultSet.getString("food_type");
            int resultExotic = resultSet.getInt("food_exotic");

            Assertions.assertEquals(name, resultName, "Названия добавляемого и добавленного товаров не совпадают");
            Assertions.assertEquals(type, resultType, "Типы добавляемого и добавленного товаров не совпадают");
            Assertions.assertEquals(exotic, resultExotic, "Экзотичность добавляемого и добавленного товаров не совпадают");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод удаления товара
     *
     * @param name   - название товара
     * @param type   - тип товара
     * @param exotic - экзотический товар (1 - true, 0 - false)
     */
    public void deleteItem(String name, String type, int exotic) {
        String queryDelete = "DELETE FROM food WHERE food_name = ? AND food_type = ? AND food_exotic = ?";
        try {
            PreparedStatement pstmtDelete = connection.prepareStatement(queryDelete);
            pstmtDelete.setString(1, name);
            pstmtDelete.setString(2, type);
            pstmtDelete.setInt(3, exotic);
            pstmtDelete.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
}
