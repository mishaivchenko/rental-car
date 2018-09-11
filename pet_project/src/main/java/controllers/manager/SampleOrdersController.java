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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "doSampleOrders", urlPatterns = "/doSampleOrders")
public class SampleOrdersController extends HttpServlet {
    private final Logger logger = Logger.getLogger(SampleOrdersController.class);
    List<OrderEntity> orderList;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        if (user!=null&&user.getUserRole().compareTo(User.Role.MANAGER) == 0) {
            String parameter = req.getParameter("status");
            orderList = ServiceFactoryImpl.getServiceFactory().getOrderService().getOrdersByParameter("status",parameter);
            session.setAttribute("Orders",orderList);
            logger.info("User "+user.getUserName() + " selected cars");
            resp.sendRedirect("/jsp/userOrders.jsp");
        } else resp.sendRedirect("/jsp/login.jsp");
    }
}