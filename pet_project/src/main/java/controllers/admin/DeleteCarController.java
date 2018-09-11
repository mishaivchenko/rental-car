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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name="deleteCar", urlPatterns = "/deleteCar")
public class DeleteCarController extends HttpServlet {
    private final Logger logger = Logger.getLogger(DeleteCarController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        int id = Integer.parseInt(req.getParameter("id"));
        if (user!=null&&user.getUserRole().compareTo(User.Role.ADMIN) == 0) {
            Car car = ServiceFactoryImpl.getServiceFactory().gerCarService().read(id);
            car.setStatus(Car.Status.OUT_OF_STOCK);
            ServiceFactoryImpl.getServiceFactory().gerCarService().update(car);
            List<Car> carList = ServiceFactoryImpl.getServiceFactory().gerCarService().read();
            session.setAttribute("Cars",carList);
            resp.sendRedirect("/jsp/cars.jsp");
        } else resp.sendRedirect("/jsp/login.jsp");
    }
}
