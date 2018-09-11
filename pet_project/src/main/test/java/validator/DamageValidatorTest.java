package java.validator;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import utill.Validator.validatorImpl.DamageValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DamageValidatorTest {
    private HttpServletRequest req = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);
    @InjectMocks
    DamageValidatorImpl damageValidator;

    @Test
    public void validateUserWithCorrectParams(){
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("crashDesc")).thenReturn("crash description");
        when(req.getParameter("sum")).thenReturn("120");

        damageValidator = new DamageValidatorImpl();
        //given
        boolean expected = true;
        //When
        boolean result = damageValidator.validate(req);
        //Then
        Assert.assertEquals(true,result);
    }
    @Test
    public void validateDamageWithIncorrectParams(){
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("crashDesc")).thenReturn(" ");
        when(req.getParameter("sum")).thenReturn("120");

        damageValidator = new DamageValidatorImpl();
        //given
        boolean expected = true;
        //When
        boolean result = damageValidator.validate(req);
        //Then
        Assert.assertEquals(false,result);
    }
}
