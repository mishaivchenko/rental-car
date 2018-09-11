package controllers;

import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "LogoutServlet",
            urlPatterns = "/Logout")
public class LogoutController extends HttpServlet {
    private final Logger logger = Logger.getLogger(LogoutController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/jsp/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        if(user!=null){
           // session.removeAttribute("User");
            logger.info(user.getUserName() +" just logout");
                session.invalidate();
        }

        resp.sendRedirect("/jsp/login.jsp");
    }
}
