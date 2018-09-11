package controllers.admin;

import entity.User;
import org.apache.log4j.Logger;
import services.serviceImpl.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "upToManager", urlPatterns = "/upClientToManager")
public class UpToManagerController extends HttpServlet {
    private final Logger logger = Logger.getLogger(UpToManagerController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        int id = Integer.parseInt(req.getParameter("id"));
        if(user!=null&&user.getUserRole().compareTo(User.Role.ADMIN) == 0){
            User userToUp = ServiceFactoryImpl.getServiceFactory().getUserService().read(id);
            userToUp.setUserRole(User.Role.MANAGER);
            ServiceFactoryImpl.getServiceFactory().getUserService().update(userToUp);
            List<User> userList = ServiceFactoryImpl.getServiceFactory().getUserService().read();
            session.setAttribute("Users",userList);
            logger.info("the system administrator up to manager the user " + userToUp.getUserName());
            resp.sendRedirect("/jsp/admin/adminUsers.jsp");
        }else resp.sendRedirect("/jsp/login.jsp");
    }
}
