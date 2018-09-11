package controllers.client;

import entity.Car;
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
@WebServlet(name = "payDamage",urlPatterns = "/payDamage")
public class PayDamageController extends HttpServlet {
    private final Logger logger = Logger.getLogger(PayDamageController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User client = (User) session.getAttribute("User");
        if(client !=null && client.getUserRole().compareTo(User.Role.CLIENT)==0){
            int id = Integer.parseInt(req.getParameter("id"));
            OrderEntity orderEntity = ServiceFactoryImpl.getServiceFactory().getOrderService().read(id);
            Car car = ServiceFactoryImpl.getServiceFactory().gerCarService().read(orderEntity.getCarId());
            orderEntity.setStatus(OrderEntity.Status.RETURN_CONFIRMED);
            car.setStatus(Car.Status.IN_STOCK);
            ServiceFactoryImpl.getServiceFactory().getOrderService().updateParamById("status",String.valueOf(orderEntity.getStatus()),id);
            ServiceFactoryImpl.getServiceFactory().gerCarService().update(car);
            logger.info("User" + client.getUserName() + "has paid damage");
            req.getRequestDispatcher("/getOrders").forward(req,resp);
        }  else resp.sendRedirect("/jsp/login.jsp");;
    }
}
