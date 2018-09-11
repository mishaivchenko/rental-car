package java.validator;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import utill.Validator.validatorImpl.DamageValidatorImpl;
import utill.Validator.validatorImpl.RejectValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RejectValidatorTest {
    private HttpServletRequest req = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);
    @InjectMocks
    RejectValidatorImpl rejectValidator;

    @Test
    public void validateUserWithCorrectParams(){
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("text")).thenReturn("some text");

        rejectValidator = new RejectValidatorImpl();
        //given
        boolean expected = true;
        //When
        boolean result = rejectValidator.validate(req);
        //Then
        Assert.assertEquals(true,result);
    }
    @Test
    public void validateDamageWithIncorrectParams(){
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("text")).thenReturn(" ");

        rejectValidator = new RejectValidatorImpl();
        //given
        boolean expected = true;
        //When
        boolean result = rejectValidator.validate(req);
        //Then
        Assert.assertEquals(false,result);
    }
}
