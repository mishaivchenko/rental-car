package controllers.client;

import entity.Damage;
import entity.OrderEntity;
import entity.User;
import controllers.manager.RedirectToCreateCrashController;
import org.apache.log4j.Logger;
import services.serviceImpl.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "redirectToPayCrash", urlPatterns = "/redirectToPayCrash")
public class RedirectToPayCrashController extends HttpServlet {
    private final Logger logger = Logger.getLogger(RedirectToCreateCrashController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User client = (User) session.getAttribute("User");
        if(client!=null&& client.getUserRole().compareTo(User.Role.CLIENT)==0) {
            int id = Integer.parseInt(req.getParameter("id"));
            Damage damage = ServiceFactoryImpl.getServiceFactory().getDamageService().getDamageByOrderId(id);
            OrderEntity orderEntity = ServiceFactoryImpl.getServiceFactory().getOrderService().read(id);
            setAttributesToRequest(req,orderEntity,id,damage);
            req.getRequestDispatcher("jsp/client/damage.jsp").forward(req,resp);
        } else resp.sendRedirect("/jsp/login.jsp");

    }
    private void setAttributesToRequest(HttpServletRequest req, OrderEntity orderEntity, int id, Damage damage){
        req.setAttribute("Order",orderEntity);
        req.setAttribute("id",id);
        req.setAttribute("Damage", damage);
    }

}
