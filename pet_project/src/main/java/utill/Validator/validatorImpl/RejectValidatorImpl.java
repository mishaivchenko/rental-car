package utill.Validator.validatorImpl;

import utill.Validator.Validator;

import javax.servlet.http.HttpServletRequest;

public class RejectValidatorImpl implements Validator<HttpServletRequest> {
    @Override
    public boolean validate(HttpServletRequest req) {
        String rejectReason = req.getParameter("text");
        return rejectReason!=null&&rejectReason.compareTo(" ")!=0;
    }
}
