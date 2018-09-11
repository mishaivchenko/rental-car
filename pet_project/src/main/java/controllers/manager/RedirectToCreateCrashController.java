package controllers.manager;

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
@WebServlet(name = "redirectToCreateCrash", urlPatterns = "/redirectToCreateCrash")
public class RedirectToCreateCrashController extends HttpServlet {
    private final Logger logger = Logger.getLogger(RedirectToCreateCrashController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User client = (User) session.getAttribute("User");
        if(client!=null&&client.getUserRole().compareTo(User.Role.MANAGER)==0) {
            int id = Integer.parseInt(req.getParameter("id"));
            OrderEntity orderEntity = ServiceFactoryImpl.getServiceFactory().getOrderService().read(id);
            req.setAttribute("Order", orderEntity);
            req.setAttribute("id",id);
            req.getRequestDispatcher("/jsp/manager/createCrash.jsp").forward(req,resp);
        }else resp.sendRedirect("/jsp/login.jsp");
    }

    }
