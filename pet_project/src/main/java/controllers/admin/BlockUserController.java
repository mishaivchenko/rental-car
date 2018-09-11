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

@WebServlet(name = "blockUser", urlPatterns = "/blockUser")
public class BlockUserController extends HttpServlet {
    private final Logger logger = Logger.getLogger(BlockUserController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        int id = Integer.parseInt(req.getParameter("id"));
        if (user!=null&& user.getUserRole().compareTo(User.Role.ADMIN) == 0) {
            User blockedUser = ServiceFactoryImpl.getServiceFactory().getUserService().read(id);
            blockedUser.setUserStatus(User.UserStatus.BLOCKED);
            ServiceFactoryImpl.getServiceFactory().getUserService().update(blockedUser);
            List<User> userList = ServiceFactoryImpl.getServiceFactory().getUserService().read();
            session.setAttribute("Users", userList);
            logger.info("the system administrator has blocked the user " + blockedUser.getUserName());
            resp.sendRedirect("/jsp/admin/adminUsers.jsp");
        } else resp.sendRedirect("/jsp/login.jsp");
    }
}
