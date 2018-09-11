package controllers.manager;

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
@WebServlet(name = "returnConfirm", urlPatterns = "/returnConfirm")
public class returnConfirmController extends HttpServlet {
    private final Logger logger = Logger.getLogger(returnConfirmController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User client = (User) session.getAttribute("User");
        if(client!=null&&client.getUserRole().compareTo(User.Role.MANAGER)==0){
            int id = Integer.parseInt(req.getParameter("id"));
            OrderEntity orderEntity = updateOrderEntity(id);
            Car car = updateCar(orderEntity.getCarId());
            ServiceFactoryImpl.getServiceFactory().getOrderService().updateParamById("status",String.valueOf(orderEntity.getStatus()),id);
            ServiceFactoryImpl.getServiceFactory().gerCarService().update(car);
            req.getRequestDispatcher("/getOrdersManager").forward(req,resp);
            logger.info("Manager " + client.getUserName() + " has confirmed return");
        } else resp.sendRedirect("/jsp/login.jsp");
    }

    private Car updateCar(int id){
        Car car = ServiceFactoryImpl.getServiceFactory().gerCarService().read(id);
        car.setStatus(Car.Status.IN_STOCK);
        return car;
    }
    private OrderEntity updateOrderEntity(int id){
        OrderEntity orderEntity = ServiceFactoryImpl.getServiceFactory().getOrderService().read(id);
        orderEntity.setStatus(OrderEntity.Status.RETURN_CONFIRMED);
        return orderEntity;
    }
}
