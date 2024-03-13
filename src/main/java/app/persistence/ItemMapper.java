package app.persistence;

import app.entities.Item;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemMapper
{

    public static List<Item> getAllItems(ConnectionPool connectionPool)
    {
        String sql = "SELECT * FROM item";
        List<Item> itemList = new ArrayList<>();

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        )
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int itemId = rs.getInt("item_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                int categoryId = rs.getInt("category_id");
                int userId = rs.getInt("user_id");
                Item item = new Item(itemId, title, description, price, categoryId, userId);
                itemList.add(item);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    private static BigDecimal parseMoneyValue(String moneyValue) {
        // Remove currency symbol and any other non-numeric characters
        moneyValue = moneyValue.replaceAll("[^\\d.]", "");
        // Parse the string into a BigDecimal
        return new BigDecimal(moneyValue);
    }

    public static void createItem(String title, String description, BigDecimal price, int categoryId, ConnectionPool connectionPool)
    {
        String sql = "INSERT INTO item (title, description, price, category_id) VALUES (?,?,?,?)";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        )
        {
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setBigDecimal(3, price);
            ps.setInt(4, categoryId);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
}
