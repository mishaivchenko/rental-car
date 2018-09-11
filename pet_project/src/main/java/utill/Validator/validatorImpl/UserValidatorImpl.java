package utill.Validator.validatorImpl;

import utill.Validator.Validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidatorImpl implements Validator<HttpServletRequest> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String NAME_PATTERN="^[a-zA-ZА-Яа-я]{4,}$";
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN="^[_A-Za-z0-9]{5,}$";
    private static final String USERNAME_PATTERN = "^[_A-Za-z0-9]{4,}$";
    @Override
    public boolean validate(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String firstName = request.getParameter("name");
        String secondName = request.getParameter("secondName");
        return checkEmail(email)&&checkPassword(password)&&checkUserName(username)&&checkFirstName(firstName)&&checkSurname(secondName);
    }
    private boolean checkUserName(String username){
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        //User user = ServiceFactoryImpl.getServiceFactory().getUserService().getUserByName(username);
        return matcher.matches();
    }

    private boolean checkPassword(String password){
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
    private boolean checkEmail(String email){
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher  =pattern.matcher(email);

        return matcher.matches();
    }
    private boolean checkFirstName(String firstName){
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(firstName);
        return matcher.matches();
    }
    private boolean checkSurname(String surname){
        return checkFirstName(surname);
    }
}
