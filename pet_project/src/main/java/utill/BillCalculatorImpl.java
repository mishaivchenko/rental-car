package utill;

public class BillCalculatorImpl implements BillCalculator {
    public static final BillCalculatorImpl  INSTANCE =new BillCalculatorImpl();
    final float driverSalaryPerDay = 25;
    @Override
    public float calculate(float rentPrice, int term, boolean driver) {

        if (driver) {
            return (rentPrice + driverSalaryPerDay) * term;
        } else return rentPrice * term;
    }
    public static BillCalculatorImpl getInstance(){
        return INSTANCE;
    }
}
