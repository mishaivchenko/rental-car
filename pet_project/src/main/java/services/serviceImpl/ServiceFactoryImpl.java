package services.serviceImpl;

import entity.Car;
import entity.Damage;
import entity.OrderEntity;
import entity.User;
import services.Service;
import services.ServiceFactory;

public class ServiceFactoryImpl implements ServiceFactory<Service> {
    private static final ServiceFactoryImpl serviceFactory = new ServiceFactoryImpl();
    private Service<Car> carService = new CarServiceImpl();
    private Service<Damage> damageService = new DamageServiceImpl();
    private Service<OrderEntity> orderEntityService = new OrderServiceImpl();
    private Service<User> useService = new UserServiceImpl();



    public static ServiceFactoryImpl getServiceFactory(){
        return serviceFactory;
    }

    @Override
    public CarServiceImpl gerCarService() {
        return (CarServiceImpl) this.carService;
    }

    @Override
    public DamageServiceImpl getDamageService() {
        return (DamageServiceImpl) this.damageService;
    }

    @Override
    public OrderServiceImpl getOrderService() { return (OrderServiceImpl) this.orderEntityService;
    }

    @Override
    public UserServiceImpl getUserService() {
        return (UserServiceImpl) this.useService;
    }

}
