package utill.Validator;


import utill.Validator.validatorImpl.*;

public interface ValidatorFactory<Validator> {
        CarValidatorImpl getCarValidatorImpl();
        CreateOrderValidatorImpl getCreateOrderValidatorImpl();
        UserValidatorImpl getUserValidatorImpl();
        DamageValidatorImpl getDamageValidatorImpl();
        RejectValidatorImpl getRejectValidatorImpl();
}
