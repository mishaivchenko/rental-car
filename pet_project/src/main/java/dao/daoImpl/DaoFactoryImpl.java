package dao.daoImpl;

import entity.Car;
import entity.Damage;
import entity.OrderEntity;
import entity.User;
import dao.Dao;
import dao.DaoFactory;

public class DaoFactoryImpl implements DaoFactory<Dao> {
    private static final DaoFactoryImpl daoFactory = new DaoFactoryImpl();
    private final Dao<User> userDao = new UserDaoImpl();
    private final Dao<Car> carDao = new CarDaoImpl();
    private final Dao<Damage> damageDao = new DamageDaoImpl();
    private final Dao<OrderEntity> orderEntityDao = new OrderDaoImpl();

    public static DaoFactoryImpl getDaoFactoryImpl() {
        return daoFactory;
    }

    @Override
    public CarDaoImpl getCarDaoImpl() {
        return (CarDaoImpl) this.carDao;
    }

    @Override
    public DamageDaoImpl getDamageDaoImpl() {
        return (DamageDaoImpl) this.damageDao;
    }

    @Override
    public OrderDaoImpl getOrderDaoImpl() {
        return (OrderDaoImpl) this.orderEntityDao;
    }

    @Override
    public UserDaoImpl getUserDaoImpl() {
        return (UserDaoImpl) this.userDao;
    }
}
