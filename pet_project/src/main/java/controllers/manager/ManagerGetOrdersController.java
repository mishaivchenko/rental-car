package controllers.manager;

import entity.OrderEntity;
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
@WebServlet(name = "getOrdersManager", urlPatterns = "/getOrdersManager")
public class ManagerGetOrdersController extends HttpServlet {
    private final Logger logger = Logger.getLogger(ManagerGetOrdersController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("User");
        if (user!=null&& user.getUserRole().compareTo(User.Role.MANAGER) == 0) {
            List<OrderEntity> orderEntityList = ServiceFactoryImpl.getServiceFactory().getOrderService().read();
            req.getSession().setAttribute("Orders",orderEntityList);
            logger.info("Manager " + user.getUserName() + " get orders");
            resp.sendRedirect("/jsp/userOrders.jsp");
        } else resp.sendRedirect("/jsp/login.jsp");;
    }
}

