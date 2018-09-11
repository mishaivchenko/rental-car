package dao;

import dao.daoImpl.CarDaoImpl;
import dao.daoImpl.DamageDaoImpl;
import dao.daoImpl.OrderDaoImpl;
import dao.daoImpl.UserDaoImpl;

import javax.naming.NamingException;

public interface DaoFactory<Dao> {

        CarDaoImpl getCarDaoImpl() throws NamingException;
        DamageDaoImpl getDamageDaoImpl();
        OrderDaoImpl getOrderDaoImpl();
        UserDaoImpl getUserDaoImpl();

}
