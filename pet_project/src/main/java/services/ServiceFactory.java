package services;

import services.serviceImpl.CarServiceImpl;
import services.serviceImpl.DamageServiceImpl;
import services.serviceImpl.OrderServiceImpl;
import services.serviceImpl.UserServiceImpl;

public interface ServiceFactory<Service> {

    CarServiceImpl gerCarService();
    DamageServiceImpl getDamageService();
    OrderServiceImpl getOrderService();
    UserServiceImpl getUserService();
}
