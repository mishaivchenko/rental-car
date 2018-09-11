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
@WebServlet(name = "redirectToEdit", urlPatterns = "/redirectToEdit")
public class RedirectToEditController extends HttpServlet {
    private final Logger logger = Logger.getLogger(RedirectToEditController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User admin = (User) session.getAttribute("User");
            if(admin!=null&&admin.getUserRole().compareTo(User.Role.ADMIN)==0){
                int id = Integer.parseInt(req.getParameter("id"));
                Car carToEdit = ServiceFactoryImpl.getServiceFactory().gerCarService().read(id);
                session.setAttribute("car",carToEdit);
                resp.sendRedirect("jsp/admin/editCar.jsp");
            } else resp.sendRedirect("/jsp/login.jsp");
    }
}
