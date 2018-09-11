package controllers.manager;

import entity.Damage;
import entity.OrderEntity;
import entity.User;
import org.apache.log4j.Logger;
import services.serviceImpl.ServiceFactoryImpl;
import utill.Validator.Validator;
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

@WebServlet(name = "createDamage",urlPatterns = "/createDamage")
public class CreateCrashController extends HttpServlet {
    private final Logger logger = Logger.getLogger(CreateCrashController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User client = (User) session.getAttribute("User");
        if (client!=null&&client.getUserRole().compareTo(User.Role.MANAGER) == 0) {
            Validator userValidator = ValidatorFactoryImpl.getValidatorFactory().getDamageValidatorImpl();
            if (userValidator.validate(req)) {
                Damage damage = getDamageFromRequest(req);
                ServiceFactoryImpl.getServiceFactory().getDamageService().create(damage);
                updateOrder(damage);
                logger.info("Manager " + client.getUserName() + " has create damage order");
                req.getRequestDispatcher("/getOrdersManager").forward(req, resp);

            } else {
                Locale locale = (Locale) req.getSession().getAttribute("LOCALE");
                ResourceBundle messages =  ResourceBundle.getBundle("i18n.messages", locale);
                req.setAttribute("IncorrectData",messages.getString("wrongData"));
                req.getRequestDispatcher("/jsp/manager/createCrash.jsp");
            }
        }else resp.sendRedirect("/jsp/login.jsp");
    }
    private Damage getDamageFromRequest(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        OrderEntity orderEntity = ServiceFactoryImpl.getServiceFactory().getOrderService().read(id);
        String damageDesc = req.getParameter("crashDesc");
        float sum = Float.parseFloat(req.getParameter("sum"));
        return  new Damage(1,orderEntity.getOrderId(),damageDesc,sum,false);
    }

    private void updateOrder(Damage damage){
        int generatedId= ServiceFactoryImpl.getServiceFactory().getDamageService().getDamageByOrderId(damage.getOrderId()).getId();
        ServiceFactoryImpl.getServiceFactory().getOrderService().updateParamById("status", String.valueOf(OrderEntity.Status.RETURN_WITH_CRASH),damage.getOrderId());
        ServiceFactoryImpl.getServiceFactory().getOrderService().updateCrashById(generatedId,damage.getOrderId());
    }
}
