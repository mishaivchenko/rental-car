package utill.Validator.validatorImpl;

import utill.Validator.ValidatorFactory;

public class ValidatorFactoryImpl implements ValidatorFactory {
    private static final ValidatorFactoryImpl validatorFactory = new ValidatorFactoryImpl();
    private final CreateOrderValidatorImpl userPassportValidator = new CreateOrderValidatorImpl();
    private final CarValidatorImpl carValidator = new CarValidatorImpl();
    private final DamageValidatorImpl damageValidator = new DamageValidatorImpl();
    private final UserValidatorImpl userValidator = new UserValidatorImpl();
    private final RejectValidatorImpl rejectValidator = new RejectValidatorImpl();

    public static ValidatorFactoryImpl getValidatorFactory(){return  validatorFactory;}
    @Override
    public CarValidatorImpl getCarValidatorImpl() {
        return this.carValidator;
    }

    @Override
    public CreateOrderValidatorImpl getCreateOrderValidatorImpl() {
        return this.userPassportValidator;
    }

    @Override
    public UserValidatorImpl getUserValidatorImpl() {
        return this.userValidator;
    }

    @Override
    public DamageValidatorImpl getDamageValidatorImpl() {
        return this.damageValidator;
    }

    @Override
    public RejectValidatorImpl getRejectValidatorImpl() {
        return rejectValidator;
    }

}
