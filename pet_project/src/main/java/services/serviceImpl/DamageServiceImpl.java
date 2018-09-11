package services.serviceImpl;

import entity.Damage;
import dao.daoImpl.DaoFactoryImpl;
import services.Service;

import java.util.List;

public class DamageServiceImpl implements Service<Damage> {

    @Override
    public boolean create(Damage element) {
        return DaoFactoryImpl.getDaoFactoryImpl().getDamageDaoImpl().create(element);
    }

    @Override
    public Damage read(int id) {
        return DaoFactoryImpl.getDaoFactoryImpl().getDamageDaoImpl().read(id);
    }

    @Override
    public List<Damage> read() {
        return DaoFactoryImpl.getDaoFactoryImpl().getDamageDaoImpl().read();
    }

    @Override
    public boolean update(Damage element) {
        return DaoFactoryImpl.getDaoFactoryImpl().getDamageDaoImpl().update(element);
    }

    @Override
    public boolean delete(int id) {
        return DaoFactoryImpl.getDaoFactoryImpl().getDamageDaoImpl().delete(id);
    }
    public Damage getDamageByOrderId(int orderId) {return DaoFactoryImpl.getDaoFactoryImpl().getDamageDaoImpl().getDamageByOrderId(orderId);}
}
