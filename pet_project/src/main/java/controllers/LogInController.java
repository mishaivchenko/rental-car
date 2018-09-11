package controllers;

import entity.User;
import org.apache.log4j.Logger;
import services.serviceImpl.ServiceFactoryImpl;
import utill.PasswordEncryption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "loginController", urlPatterns = {"/Login"})
public class LogInController extends HttpServlet {
    private final Logger logger = Logger.getLogger(LogInController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("attempt to call the doGet method in the login controller ");
    resp.sendRedirect("/login.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Locale locale = (Locale) req.getSession().getAttribute("LOCALE");
       String username = req.getParameter("username");
       String password = req.getParameter("password");
        User userFromDb = ServiceFactoryImpl.getServiceFactory().getUserService().getUserByName(username);
        if(userFromDb!=null&&PasswordEncryption.checkPassword(password,userFromDb.getPassword())){
            req.getSession().setAttribute("User", userFromDb);
            req.setAttribute("Welcome","Welcome to " + userFromDb.getUserRole() +   " page");
            logger.info("User " + userFromDb.getUserName() + "user misha logged in");
            req.getRequestDispatcher("/jsp/home.jsp").forward(req,resp);
        } else {
            ResourceBundle messages =  ResourceBundle.getBundle("i18n.messages", locale);
            req.getSession().setAttribute("errorLoginPassMessage",messages.getString("wrongLoginOrPassword"));
            resp.sendRedirect("/jsp/login.jsp");
        }
    }
}
