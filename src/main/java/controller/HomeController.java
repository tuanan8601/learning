package controller;

import model.Comment;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

public class HomeController extends MyController {

    public void process(final HttpServletRequest request, final HttpServletResponse response, final ServletContext servletContext, final ITemplateEngine templateEngine) throws Exception {
        super.process(request, response, servletContext, templateEngine);

        templateEngine.process("index", ctx, response.getWriter());
    }
}