package listeners;

import controllers.LogInController;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {
    private final Logger logger = Logger.getLogger(LogInController.class);
    @Override
    public void requestDestroyed(ServletRequestEvent event) {

        HttpServletRequest req = (HttpServletRequest) event.getServletRequest();
        logger.info("Request Initialized for " + req.getRequestURI());
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
    logger.info("Request Destroyed: " + event.getServletRequest().getAttribute("lifecycle"));
    }
}
