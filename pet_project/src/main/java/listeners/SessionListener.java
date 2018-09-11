package listeners;

import controllers.LogInController;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
@WebListener
public class SessionListener implements HttpSessionAttributeListener {
    private final Logger logger = Logger.getLogger(LogInController.class);
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if(event.getName().compareTo("User")==0){
            User user = (User) event.getValue();
          logger.info("User " + user.getUserName() + " is logged in");
        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if(event.getName().compareTo("User")==0){
            User user = (User) event.getValue();
            logger.info("User " + user.getUserName() + " is logged out");
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}
