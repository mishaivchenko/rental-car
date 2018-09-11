package utill.Validator.validatorImpl;

import utill.Validator.Validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateOrderValidatorImpl implements Validator<HttpServletRequest> {
    private static final String NAME_PATTERN="^[a-zA-ZА-Яа-я]{3,}$";
    @Override
    public boolean validate(HttpServletRequest req) {
        String firstName = req.getParameter("customerName");
        String surname = req.getParameter("customerSurname");
        int leaseTerm = Integer.parseInt(req.getParameter("leaseTerm"));
        return checkFirstName(firstName)&&checkSurname(surname)&&checkLeaseTerm(leaseTerm);
    }

    private boolean checkFirstName(String firstName){
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(firstName);
        return matcher.matches();
    }
    private boolean checkSurname(String surname){
    return checkFirstName(surname);
    }
    private boolean checkLeaseTerm(int leaseTerm){
        return leaseTerm>0;
    }

}
