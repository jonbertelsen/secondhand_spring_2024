package app.entities;

import java.math.BigDecimal;

public class Item
{
    private int itemId;
    private String title;
    private String description;
    private BigDecimal price;
    private int categoryId;
    private int userId;

    public Item(int itemId, String title, String description, BigDecimal price, int categoryId, int userId)
    {
        this.itemId = itemId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public int getItemId()
    {
        return itemId;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public int getUserId()
    {
        return userId;
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "itemId=" + itemId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                '}';
    }
}
