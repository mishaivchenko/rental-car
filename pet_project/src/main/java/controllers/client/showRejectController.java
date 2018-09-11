package controllers.client;

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

@WebServlet(name = "showReject",urlPatterns = "/showReject")
public class showRejectController extends HttpServlet {
    private final Logger logger = Logger.getLogger(showRejectController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User client = (User) session.getAttribute("User");
        if(client!=null&&client.getUserRole().compareTo(User.Role.CLIENT)==0){
            int id = Integer.parseInt(req.getParameter("id"));
            OrderEntity orderEntity = ServiceFactoryImpl.getServiceFactory().getOrderService().read(id);
           req.setAttribute("Order", orderEntity);
            req.getRequestDispatcher("/jsp/client/reject.jsp").forward(req,resp);
            logger.info("User" + client.getUserName() + "get reject");
        } else resp.sendRedirect("/jsp/login.jsp");
    }
}
