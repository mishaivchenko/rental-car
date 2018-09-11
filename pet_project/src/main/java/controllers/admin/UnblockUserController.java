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

@WebServlet(name = "unblockUser", urlPatterns = "/unblockUser")
public class UnblockUserController extends HttpServlet {
    private final Logger logger = Logger.getLogger(UnblockUserController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        int id = Integer.parseInt(req.getParameter("id"));
        if (user!=null&&user.getUserRole().compareTo(User.Role.ADMIN) == 0) {
            User unblockUser = ServiceFactoryImpl.getServiceFactory().getUserService().read(id);
            unblockUser.setUserStatus(User.UserStatus.NONBLOCKING);
            ServiceFactoryImpl.getServiceFactory().getUserService().update(unblockUser);
            List<User> userList = ServiceFactoryImpl.getServiceFactory().getUserService().read();
            session.setAttribute("Users",userList);
            logger.info("the system administrator has unblocked the user " + unblockUser.getUserName());
            resp.sendRedirect("/jsp/admin/adminUsers.jsp");
        } else resp.sendRedirect("/jsp/login.jsp");
    }
}
