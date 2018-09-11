package java.dao;

import dao.ConnectionPool;

import entity.Car;
import entity.OrderEntity;
import entity.User;
import config.PropertiesManager;
import dao.daoImpl.CarDaoImpl;
import dao.daoImpl.OrderDaoImpl;
import dao.daoImpl.UserDaoImpl;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderDaoTest {
    TestDb testDb;
    @InjectMocks
    UserDaoImpl userDao;
    @InjectMocks
    CarDaoImpl carDao;
    @InjectMocks
    OrderDaoImpl orderDao;

    private PropertiesManager propertiesManager = new PropertiesManager();
    @Before
    public void setUp() throws SQLException, LiquibaseException {
        MockitoAnnotations.initMocks(this);
        TestDb.prepareDb(propertiesManager.getH2Properties());
        userDao = new UserDaoImpl();

    }
    @After
    public void shutDown() throws DatabaseException, LockException {
        TestDb.dropDb();
    }
    @Test
    public void addIOrderTest() throws SQLException {
        //Given
        ConnectionPool connectionPooll = mock(ConnectionPool.class);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
        userDao.setConnectionPool(connectionPooll);
        carDao.setConnectionPool(connectionPooll);
        orderDao.setConnectionPool(connectionPooll);
        User user = new User(1,"misha","ivchenko","mishaivchenko","admin","mishaivchenko@gmail.com",User.Role.ADMIN,User.UserStatus.NONBLOCKING);
        Car car = new Car(1,"mercedes","sl-500",Car.QualityClass.A,2001,20.0f,Car.Status.IN_STOCK);
        OrderEntity orderEntity = new OrderEntity(1,1,1,0,
                OrderEntity.Status.ORDERED, new Date(6),2,true,user.getFirstName(),user.getFirstName(), 22.0f,"ejevika");
        //When
        userDao.create(user);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
        carDao.create(car);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
        orderDao.create(orderEntity);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
        OrderEntity orderEntityFromDb = orderDao.read(1);
        //Then
        Assert.assertEquals(orderEntity.getOrderId(),orderEntityFromDb.getOrderId());
    }

}
