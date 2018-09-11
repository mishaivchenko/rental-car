package utill.Validator.validatorImpl;

import utill.Validator.Validator;

import javax.servlet.http.HttpServletRequest;

public class DamageValidatorImpl implements Validator<HttpServletRequest > {
    @Override
    public boolean validate(HttpServletRequest req) {
        String damageDesc = req.getParameter("crashDesc");
        float sum = Float.parseFloat(req.getParameter("sum"));
        return validateSum(sum)&&validateDamageDesc(damageDesc);
    }

    private boolean validateSum(float sum){
        return sum>0;
    }
    private boolean validateDamageDesc(String damageDesc){
        return damageDesc!=null&&damageDesc.compareTo(" ")!=0;
    }
}
