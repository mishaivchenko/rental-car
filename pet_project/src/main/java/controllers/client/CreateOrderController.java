package controllers.client;

import entity.Car;
import entity.OrderEntity;
import entity.User;
import org.apache.log4j.Logger;
import services.serviceImpl.ServiceFactoryImpl;
import utill.Validator.validatorImpl.CreateOrderValidatorImpl;
import utill.Validator.validatorImpl.ValidatorFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "createOrder", urlPatterns = "/createOrder")
public class CreateOrderController extends HttpServlet {
    private final Logger logger = Logger.getLogger(CreateOrderController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User client = (User) session.getAttribute("User");
        if(client!=null&&client.getUserRole().compareTo(User.Role.CLIENT)==0) {
            CreateOrderValidatorImpl userValidator = ValidatorFactoryImpl.getValidatorFactory().getCreateOrderValidatorImpl();
            if (userValidator.validate(req)) {
                OrderEntity orderEntity = createOrderFromRequest(req, client.getUserId());
                Car car = ServiceFactoryImpl.getServiceFactory().gerCarService().read(orderEntity.getCarId());
                orderEntity.setCarModelYear(car.getCarName() + " " + car.getModel() + " " + car.getYear());
                ServiceFactoryImpl.getServiceFactory().getOrderService().create(orderEntity);
                car.setStatus(Car.Status.OUT_OF_STOCK);
                ServiceFactoryImpl.getServiceFactory().gerCarService().update(car);
                logger.info("User" + client.getUserName() + " has created order on car " + orderEntity.getCarModelYear());
                resp.sendRedirect("jsp/userOrders.jsp");
            } else {
                Locale locale = (Locale) req.getSession().getAttribute("LOCALE");
                ResourceBundle messages =  ResourceBundle.getBundle("i18n.messages", locale);
                req.setAttribute("IncorrectData",messages.getString("wrongData"));
                req.getRequestDispatcher("/jsp/client/createOrder.jsp").forward(req, resp);
            }
        } else resp.sendRedirect("/jsp/login.jsp");
    }

    private OrderEntity createOrderFromRequest(HttpServletRequest req, int id){
        boolean driver;
        String firstName = req.getParameter("customerName");
        String surname = req.getParameter("customerSurname");
        driver = req.getParameter("driver") != null;
        int carId = Integer.parseInt(String.valueOf(req.getSession().getAttribute("id")));
        int leaseTerm = Integer.parseInt(req.getParameter("leaseTerm"));
        return new OrderEntity(1,id,carId,0,OrderEntity.Status.ORDERED,new Date(6),leaseTerm,driver,firstName,surname,0.0f,null);
    }
}
