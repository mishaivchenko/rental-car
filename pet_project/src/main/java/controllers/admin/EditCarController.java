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

@WebServlet(name = "editCar", urlPatterns = "/editCar")
public class EditCarController extends HttpServlet {
    private final Logger logger = Logger.getLogger(EditCarController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User admin = (User) session.getAttribute("User");
        if(admin!=null && admin.getUserRole().compareTo(User.Role.ADMIN)==0){
            Car car = createCarFromRequest(req);
            ServiceFactoryImpl.getServiceFactory().gerCarService().update(car);
            session.removeAttribute("car");
            logger.info("Car with Id" + car.getId() + " has been changed");
            resp.sendRedirect("/getCars");
        } else resp.sendRedirect("/jsp/login.jsp");
    }
    private Car createCarFromRequest(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        String mark = req.getParameter("carName");
        String model = req.getParameter("model");
        String qualityClass = req.getParameter("class");
        int year = Integer.parseInt(req.getParameter("year"));
        Float rentPrice = Float.parseFloat(req.getParameter("rentPrice"));
        Car.Status status = ServiceFactoryImpl.getServiceFactory().gerCarService().read(id).getStatus();
        return new Car(1,mark,model,Car.QualityClass.valueOf(qualityClass),year,rentPrice,status);
    }
}
