package app;

import app.config.ThymeleafConfig;
import app.entities.Category;
import app.entities.Item;
import app.persistence.CategoryMapper;
import app.persistence.ConnectionPool;
import app.persistence.ItemMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main
{
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "secondhand";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args)
    {
        // Initializing Javalin and Jetty webserver

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);

        // Routing

        app.get("/", ctx ->  index(ctx));
        app.get("/createitem", ctx -> ctx.render("createitem.html"));
    }

    public static void index(Context ctx)
    {
        ctx.sessionAttribute("hej", "her er en tekst");

        Map<Integer, Category> categoryMap = CategoryMapper.getAllCategories(connectionPool);

        ctx.sessionAttribute("categoryMap", categoryMap);

        List<Item> itemList = ItemMapper.getAllItems(connectionPool);
        ctx.attribute("itemList", itemList);
        ctx.render("index.html");
    }

}