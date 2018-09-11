package controllers.manager;

import entity.Car;
import entity.OrderEntity;
import entity.User;
import org.apache.log4j.Logger;
import services.serviceImpl.ServiceFactoryImpl;
import utill.Validator.validatorImpl.RejectValidatorImpl;
import utill.Validator.validatorImpl.ValidatorFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "createReject", urlPatterns = "/createReject")
public class CreateRejectController extends HttpServlet {
    private final Logger logger = Logger.getLogger(CreateRejectController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("User");
        if (user!=null&&user.getUserRole().compareTo(User.Role.MANAGER) == 0) {
            RejectValidatorImpl carValidator = ValidatorFactoryImpl.getValidatorFactory().getRejectValidatorImpl();
            if(carValidator.validate(req)) {
                int id = Integer.parseInt(req.getParameter("id"));
                String rejectReason = req.getParameter("text");
                OrderEntity orderEntity = updateOrderEntity(id, rejectReason);
                Car car = updateCar(orderEntity.getCarId());
                ServiceFactoryImpl.getServiceFactory().getOrderService().updateParamById("rejectionReason", orderEntity.getRejectionReason(), orderEntity.getOrderId());
                ServiceFactoryImpl.getServiceFactory().getOrderService().updateParamById("status", String.valueOf(orderEntity.getStatus()), orderEntity.getOrderId());
                ServiceFactoryImpl.getServiceFactory().gerCarService().update(car);
                resp.sendRedirect("/getOrdersManager");
                logger.info("Manager " + user.getUserName() + " has created reject");
            } else {
                Locale locale = (Locale) req.getSession().getAttribute("LOCALE");
                ResourceBundle messages =  ResourceBundle.getBundle("i18n.messages", locale);
                req.setAttribute("IncorrectData",messages.getString("wrongData"));
                req.getRequestDispatcher("/jsp/createReject.jsp").forward(req,resp);
            }

        } else resp.sendRedirect("/jsp/login.jsp");
    }
    private Car updateCar(int id){
        Car car = ServiceFactoryImpl.getServiceFactory().gerCarService().read(id);
        car.setStatus(Car.Status.IN_STOCK);
        return car;
    }
    private OrderEntity updateOrderEntity(int id, String reason){
        OrderEntity orderEntity = ServiceFactoryImpl.getServiceFactory().getOrderService().read(id);
        orderEntity.setStatus(OrderEntity.Status.NEGATIVE);
        orderEntity.setRejectionReason(reason);
        return orderEntity;
    }

}
