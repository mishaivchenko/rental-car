package controllers.admin;

import entity.Car;
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

@WebServlet(name = "getCars", urlPatterns = "/getCars")
public class GetCarsController extends HttpServlet {
    private final Logger logger = Logger.getLogger(GetCarsController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("User");
        List<Car> carList;
        if (user!=null&&(user.getUserRole().compareTo(User.Role.ADMIN) == 0 || user.getUserRole().compareTo(User.Role.CLIENT) == 0)) {
            if(user.getUserRole().compareTo(User.Role.ADMIN)==0){
                carList =ServiceFactoryImpl.getServiceFactory().gerCarService().read();
            } else {
                carList = ServiceFactoryImpl.getServiceFactory().gerCarService().getCarsByParameter("status", "IN_STOCK");
            }
            logger.info("User "+ user.getUserName() + "get a List of cars");
            req.getSession().setAttribute("Cars", carList);
            resp.sendRedirect("/jsp/cars.jsp");

        } else resp.sendRedirect("/jsp/login.jsp");
    }
}
