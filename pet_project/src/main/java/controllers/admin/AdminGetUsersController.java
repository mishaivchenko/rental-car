package controllers.admin;

import entity.User;
import org.apache.log4j.Logger;
import services.serviceImpl.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "getUsers", urlPatterns = "/getUsers")
public class AdminGetUsersController extends HttpServlet {
    private final Logger logger = Logger.getLogger(AdminGetUsersController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("User");
            if(user.getUserRole().compareTo(User.Role.ADMIN)==0){
                List<User> userList = ServiceFactoryImpl.getServiceFactory().getUserService().read();
                req.getSession().setAttribute("Users", userList);
                logger.info(user.getUserName() + " get list of users");
                resp.sendRedirect("/jsp/admin/adminUsers.jsp");

            } else resp.sendRedirect("/jsp/login.jsp");
    }
}
