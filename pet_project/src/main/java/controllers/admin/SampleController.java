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
import java.util.stream.Collectors;

@WebServlet(name = "sample", urlPatterns = "/doSample")
public class SampleController extends HttpServlet {
    private final Logger logger = Logger.getLogger(SampleController.class);
    List<Car> carList = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        if (user != null && (user.getUserRole().compareTo(User.Role.CLIENT) == 0 || user.getUserRole().compareTo(User.Role.ADMIN) == 0)) {
            String markParam = req.getParameter("mark");
            String classParam = req.getParameter("class");
            String sortParam = req.getParameter("sort");
            carList = ServiceFactoryImpl.getServiceFactory().gerCarService().getCarsWithSample(markParam, classParam);
            if (carList == null) {
                carList = (List<Car>) session.getAttribute("Cars");
            }
            if (sortParam.compareTo(" ") > 0) {
                carList = ServiceFactoryImpl.getServiceFactory().gerCarService().doSort(sortParam, carList);
            }
            if(user.getUserRole().compareTo(User.Role.CLIENT) == 0) {
                carList = carList.stream().filter(i->i.getStatus().compareTo(Car.Status.IN_STOCK)==0).collect(Collectors.toList());
            }
            logger.info("User "+user.getUserName() + " selected cars");
            session.setAttribute("Cars", carList);
            resp.sendRedirect("/jsp/cars.jsp");
        } else resp.sendRedirect("/jsp/login.jsp");
    }
}