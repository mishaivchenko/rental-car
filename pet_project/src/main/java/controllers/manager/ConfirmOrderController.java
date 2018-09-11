package controllers.manager;

import entity.Car;
import entity.OrderEntity;
import entity.User;
import org.apache.log4j.Logger;
import services.serviceImpl.ServiceFactoryImpl;
import utill.BillCalculator;
import utill.BillCalculatorImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "confirmOrder", urlPatterns = "/confirmOrder")
public class ConfirmOrderController extends HttpServlet {
    private final Logger logger = Logger.getLogger(ConfirmOrderController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BillCalculator billCalculator = BillCalculatorImpl.getInstance();
        User user = (User) req.getSession().getAttribute("User");
        if (user.getUserRole().compareTo(User.Role.MANAGER) == 0) {
            int id = Integer.parseInt(req.getParameter("id"));
            OrderEntity orderEntity = ServiceFactoryImpl.getServiceFactory().getOrderService().read(id);
            Car car = ServiceFactoryImpl.getServiceFactory().gerCarService().read(orderEntity.getCarId());
            orderEntity.setStatus(OrderEntity.Status.ORDER_CONFIRMED);
            orderEntity.setSum(billCalculator.calculate(car.getRentPrice(),orderEntity.getLeaseTerm(),orderEntity.isDriver()));
            ServiceFactoryImpl.getServiceFactory().getOrderService().updateSumById(orderEntity.getSum(),orderEntity.getOrderId());
            ServiceFactoryImpl.getServiceFactory().getOrderService().updateParamById("status",String.valueOf(orderEntity.getStatus()),orderEntity.getOrderId());
            logger.info("Manager" + user.getUserName() + "has confirmed order");
            resp.sendRedirect("/getOrdersManager");
        } else resp.sendRedirect("/jsp/login.jsp");
    }
}
