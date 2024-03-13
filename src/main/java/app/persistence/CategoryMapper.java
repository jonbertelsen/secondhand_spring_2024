package app.persistence;

import app.entities.Category;
import app.entities.Item;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryMapper
{
    // TODO: Hent alle categories

    public static Map<Integer, Category> getAllCategories(ConnectionPool connectionPool)
    {
        String sql = "SELECT * FROM category";

        Map<Integer, Category> categoryMap = new HashMap<>();

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        )
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int categoryId = rs.getInt("category_id");
                String name = rs.getString("name");
                Category category = new Category(categoryId, name);
                categoryMap.put(categoryId, category);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return categoryMap;
    }

}
