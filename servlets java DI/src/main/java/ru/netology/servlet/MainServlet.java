package ru.netology.servlet;

import ru.netology.config.JavaConfig;
import ru.netology.controller.PostController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    private PostController postController;
    public static final String API_POSTS = "/api/posts";
    public static final String API_POSTS_ID = "/api/posts/\\d+";
    public static final String STR = "/";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";

    public static final String METHOD_DELETE = "DELETE";

    @Override
    public void init() {
        final var context = new AnnotationConfigApplicationContext(JavaConfig.class);
        postController = context.getBean(PostController.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();

            if (method.equals(METHOD_GET) && path.equals(API_POSTS)) {
                postController.all(resp);
                return;
            }
            if (method.equals(METHOD_GET) && path.matches(API_POSTS_ID)) {
                final var id = Long.parseLong(path.substring(path.lastIndexOf(STR)+1));
                postController.getById(id, resp);
                return;
            }
            if (method.equals(METHOD_POST) && path.equals(API_POSTS)) {
                postController.save(req.getReader(), resp);
                return;
            }
            if (method.equals(METHOD_DELETE) && path.matches(API_POSTS_ID)) {
                final var id = Long.parseLong(path.substring(path.lastIndexOf(STR)+1));
                postController.removeById(id, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}