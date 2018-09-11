package utill.Validator.validatorImpl;

import entity.Car;
import utill.Validator.Validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarValidatorImpl implements Validator<HttpServletRequest> {
    private static final String CAR_NAME_PATTERN="^[a-zA-ZА-Яа-я]{2,}$";
    private static final String CAR_MODEL_PATTERN="^[a-zA-ZА-Яа-я]\\+-{0,}$";
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean validate(HttpServletRequest req) {
        String carName = req.getParameter("carName");

        String model = req.getParameter("model");

        String qualityClass = req.getParameter("class");
        int year = Integer.parseInt(req.getParameter("year"));
        Float rentPrice = Float.parseFloat(req.getParameter("rentPrice"));
        return validateMark(carName)&&validateQualityClass(qualityClass)&&validateYear(year)&&validateRentPrice(rentPrice);
    }

    private boolean validateMark(String mark) {
        pattern = Pattern.compile(CAR_NAME_PATTERN);
        matcher = pattern.matcher(mark);
        return matcher.matches();
    }

    private boolean validateQualityClass(String qualityClass){
        Car.QualityClass[] mas = Car.QualityClass.values();
        for (Car.QualityClass carClass:mas) {
            if(String.valueOf(carClass).compareTo(qualityClass)==0) return true;
        }
        return false;
    }
    private boolean validateYear(int year){
        return year>1950&&year<2018;
    }
    private boolean validateRentPrice(float rentPrice){
        return rentPrice>5;
    }
}
