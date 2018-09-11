package java.validator;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import utill.Validator.validatorImpl.CarValidatorImpl;
import utill.Validator.validatorImpl.UserValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserValidatorTest {
    private HttpServletRequest req = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);
    @InjectMocks
    UserValidatorImpl userValidator;

    @Test
    public void validateUserWithCorrectParams(){
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("password")).thenReturn("password");
        when(req.getParameter("email")).thenReturn("email@gmail.com");
        when(req.getParameter("username")).thenReturn("username");
        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("secondName")).thenReturn("secondName");
        userValidator = new UserValidatorImpl();
        //given
        boolean expected = true;
        //When
        boolean result = userValidator.validate(req);
        //Then
        Assert.assertEquals(true,result);
    }

    @Test
    public void validateUserWithInCorrectParams(){
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getSession()).thenReturn(session);
        when(req.getParameter("password")).thenReturn("password[]");
        when(req.getParameter("email")).thenReturn("@email@gmail.com");
        when(req.getParameter("username")).thenReturn(" ");
        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("secondName")).thenReturn("secondName");
        userValidator = new UserValidatorImpl();
        //given
        boolean expected = false;
        //When
        boolean result = userValidator.validate(req);
        //Then
        Assert.assertEquals(expected,result);
    }
}
