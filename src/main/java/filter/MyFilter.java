package filter;

import application.MyApplication;
import controller.IController;
import org.thymeleaf.ITemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter {
    private ServletContext servletContext;
    private MyApplication application;

    private boolean process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getRequestURI().startsWith("/api")) {
            return false;
        }
        if (request.getRequestURI().endsWith(".css") || request.getRequestURI().startsWith("/images") || request.getRequestURI().startsWith("/favicon") || request.getRequestURI().startsWith("/templates")|| request.getRequestURI().startsWith("/js")) {
            // System.out.println(request.getRequestURI());
            return false;
        }
        IController controller = this.application.resolveControllerForRequest(request);
        ITemplateEngine templateEngine = this.application.templateEngine;
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        controller.process(request, response, this.servletContext, templateEngine);
        return true;
    }
}
