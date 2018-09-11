package java.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import utill.Validator.validatorImpl.CarValidatorImpl;
import utill.Validator.validatorImpl.ValidatorFactoryImpl;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CarValidatorTest {
/*    @Mock
    HttpServletRequest req;

    CarValidatorImpl carValidator;*/
@InjectMocks
CarValidatorImpl carValidator;

    private HttpServletRequest req = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);

    @Test
    public void validateCarWithCorrectParams(){
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("carName")).thenReturn("BMW");
        when(req.getParameter("model")).thenReturn("x-5");
        when(req.getParameter("class")).thenReturn("A");
        when(req.getParameter("year")).thenReturn("2000");
        when(req.getParameter("rentPrice")).thenReturn("20");
        carValidator = new CarValidatorImpl();
       //given
       boolean expected = true;
       //When
        boolean result = carValidator.validate(req);
        //Then
        Assert.assertEquals(true,result);
    }

    @Test
    public void validateCarWithIncorrectParams(){
        //Given
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("carName")).thenReturn("BMW[]");
        when(req.getParameter("model")).thenReturn("x-5");
        when(req.getParameter("class")).thenReturn("0");
        when(req.getParameter("year")).thenReturn("21");
        when(req.getParameter("rentPrice")).thenReturn("20");
        carValidator = new CarValidatorImpl();
        boolean expected = false;
        //When
        boolean result = carValidator.validate(req);
        //Then
        Assert.assertEquals(false,result);
    }
}
