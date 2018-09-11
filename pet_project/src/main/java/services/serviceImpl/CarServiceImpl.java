package services.serviceImpl;

import entity.Car;
import dao.daoImpl.DaoFactoryImpl;
import services.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements Service<Car> {

    @Override
    public boolean create(Car element) {
        return DaoFactoryImpl.getDaoFactoryImpl().getCarDaoImpl().create(element);
    }

    @Override
    public Car read(int id) {
        return DaoFactoryImpl.getDaoFactoryImpl().getCarDaoImpl().read(id);
    }

    @Override
    public List<Car> read() {
        return DaoFactoryImpl.getDaoFactoryImpl().getCarDaoImpl().read();
    }

    @Override
    public boolean update(Car element) {
        return DaoFactoryImpl.getDaoFactoryImpl().getCarDaoImpl().update(element);
    }

    @Override
    public boolean delete(int id) {
        return DaoFactoryImpl.getDaoFactoryImpl().getCarDaoImpl().delete(id);
    }

    public List<Car> getCarsByParameter(String param, String value) {
        return DaoFactoryImpl.getDaoFactoryImpl().getCarDaoImpl().getCarsByParameter(param, value);
    }

    public List<Car> getCarsWithSample(String markParam, String classParam) {
        List<Car> carList = null;
        if ((markParam.compareTo(" ")>0 && (classParam.compareTo(" ")>0))) {
            carList = ServiceFactoryImpl.getServiceFactory().gerCarService().getCarsByParameter("carName", markParam);
            carList = carList.stream().filter(i -> i.getQualityClass().compareTo(Car.QualityClass.valueOf(classParam)) == 0).collect(Collectors.toList());
            return carList;
        } else if ((markParam.compareTo(" ")>0 && (classParam.compareTo(" ")<=0))) {
            carList = ServiceFactoryImpl.getServiceFactory().gerCarService().getCarsByParameter("carName", markParam);
            return carList;
        } else if ((markParam.compareTo(" ") <=0 && (classParam.compareTo(" ")>0))) {
            carList = ServiceFactoryImpl.getServiceFactory().gerCarService().getCarsByParameter("qualityClass", classParam);

            return carList;
        }
        return carList;
    }
    public List<Car> doSort(String sortParam,List<Car> list){
        if(sortParam.compareTo("mark")==0){
            return list.stream().sorted(Comparator.comparing(Car::getCarName)).collect(Collectors.toList());
        } else {
            return list.stream().sorted(Comparator.comparing(Car::getRentPrice)).collect(Collectors.toList());
        }
    }
}