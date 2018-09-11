package services.serviceImpl;

import entity.OrderEntity;
import dao.daoImpl.DaoFactoryImpl;
import services.Service;

import java.util.List;

public class OrderServiceImpl implements Service<OrderEntity> {

    @Override
    public boolean create(OrderEntity element) {
        return DaoFactoryImpl.getDaoFactoryImpl().getOrderDaoImpl().create(element);
    }
    @Override
    public OrderEntity read(int id) {
        return DaoFactoryImpl.getDaoFactoryImpl().getOrderDaoImpl().read(id);
    }
    @Override
    public List<OrderEntity> read() {
        return DaoFactoryImpl.getDaoFactoryImpl().getOrderDaoImpl().read();
    }
    @Override
    public boolean update(OrderEntity element) { return DaoFactoryImpl.getDaoFactoryImpl().getOrderDaoImpl().update(element); }
    @Override
    public boolean delete(int id) {
        return DaoFactoryImpl.getDaoFactoryImpl().getOrderDaoImpl().delete(id);
    }

    public List<OrderEntity> getOrdersByParameter(String param, String value){return DaoFactoryImpl.getDaoFactoryImpl().getOrderDaoImpl().getOrdersByParameter(param,value);}
    public List<OrderEntity> getOrdersByUserId(int id){return DaoFactoryImpl.getDaoFactoryImpl().getOrderDaoImpl().getOrdersByUserId(id);}
    public boolean updateParamById(String param,String value, int id){ return DaoFactoryImpl.getDaoFactoryImpl().getOrderDaoImpl().updateParamById(param,value,id);}
    public boolean updateSumById(float sum, int id){ return DaoFactoryImpl.getDaoFactoryImpl().getOrderDaoImpl().updateSumById(sum,id);}
    public boolean updateCrashById(int crashId, int id) {return DaoFactoryImpl.getDaoFactoryImpl().getOrderDaoImpl().updateCrashById(crashId,id);}
}
