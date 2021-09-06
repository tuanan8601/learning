package controller;

import DAO.ISubjectDAO;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public abstract class MyController implements IController {
    WebContext ctx;

//    ISubjectDAO movieDAO = new DAO.;
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {

        String lang = request.getParameter("lang");
        Locale locale = new Locale("vi");

        ctx = new WebContext(request, response, servletContext, locale);
    }
}