package controllers.client;

import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "redirectToCreateOrder", value = "/redirectToCreateOrder")
public class redirectToCreateOrderController extends HttpServlet {
    private final Logger logger = Logger.getLogger(redirectToCreateOrderController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User client = (User) session.getAttribute("User");
        if (client!=null&&client.getUserRole().compareTo(User.Role.CLIENT) == 0) {
            int id = Integer.parseInt(req.getParameter("id"));
            session.setAttribute("id",id);
            resp.sendRedirect("/jsp/client/createOrder.jsp");
        } else resp.sendRedirect("/jsp/login.jsp");;
    }
}
