package controllers.admin;

import entity.Car;
import entity.User;
import org.apache.log4j.Logger;
import services.serviceImpl.ServiceFactoryImpl;
import utill.Validator.validatorImpl.CarValidatorImpl;
import utill.Validator.validatorImpl.ValidatorFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "addCar", urlPatterns = "/addCar")
public class AdminAddCarController extends HttpServlet {
    private final Logger logger = Logger.getLogger(AdminAddCarController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User admin = (User) session.getAttribute("User");

        if(admin!=null&&admin.getUserRole().compareTo(User.Role.ADMIN)==0){
            CarValidatorImpl carValidator = ValidatorFactoryImpl.getValidatorFactory().getCarValidatorImpl();
            if(carValidator.validate(req)) {
                Car car = createCarFromRequest(req);
                ServiceFactoryImpl.getServiceFactory().gerCarService().create(car);
                req.setAttribute("result", car.getCarName() + " " + car.getModel());
                logger.info("Car " + car.getCarName() + " " + car.getModel() + " " + "successfully added to database");
                req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
            } else {
                Locale locale = (Locale) req.getSession().getAttribute("LOCALE");
                ResourceBundle messages =  ResourceBundle.getBundle("i18n.messages", locale);
                req.setAttribute("IncorrectData",messages.getString("wrongData"));
                req.getRequestDispatcher("/jsp/admin/createCar.jsp").forward(req,resp);
            }
        } else resp.sendRedirect("/jsp/login.jsp");
    }


    private Car createCarFromRequest(HttpServletRequest req){
        String carName = req.getParameter("carName");
        String model = req.getParameter("model");
        String qualityClass = req.getParameter("class");
        int year = Integer.parseInt(req.getParameter("year"));
        Float rentPrice = Float.parseFloat(req.getParameter("rentPrice"));
        return new Car(1,carName,model,Car.QualityClass.valueOf(qualityClass),year,rentPrice,Car.Status.IN_STOCK);
    }
    }

