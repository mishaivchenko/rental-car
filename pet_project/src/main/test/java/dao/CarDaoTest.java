package java.dao;
import dao.ConnectionPool;
import entity.Car;
import config.PropertiesManager;
import dao.daoImpl.CarDaoImpl;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;


import java.sql.SQLException;

public class CarDaoTest {
    TestDb testDb;
    @InjectMocks CarDaoImpl carDao;
    private PropertiesManager propertiesManager = new PropertiesManager();
    @Before
    public void setUp() throws SQLException, LiquibaseException {
        MockitoAnnotations.initMocks(this);
        TestDb.prepareDb(propertiesManager.getH2Properties());
        CarDaoImpl carDao = new CarDaoImpl();

    }
    @After
    public void shutDown() throws DatabaseException, LockException {
        TestDb.dropDb();
    }

     @Test
    public void addCarTest() throws SQLException {
       //Given
        ConnectionPool connectionPooll = mock(ConnectionPool.class);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));

        carDao.setConnectionPool(connectionPooll);
       Car car = new Car(1,"mercedes","sl-500",Car.QualityClass.A,2001,20.0f,Car.Status.IN_STOCK);
        //When
       boolean result =carDao.create(car);
       carDao.setConnectionPool(connectionPooll);
       when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
       Car carFromDb = carDao.read(1);
       //Then
       Assert.assertTrue(result);
       Assert.assertEquals(car,carFromDb);
    }

    @Test
    public void updateCarTest() throws SQLException {
        //Given
        ConnectionPool connectionPooll = mock(ConnectionPool.class);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
        carDao.setConnectionPool(connectionPooll);
        Car beforeUpdate = new Car(1,"mercedes","sl-500",Car.QualityClass.A,2001,20.0f,Car.Status.IN_STOCK);
        Car afterUpdate = new Car(1,"BMW","X5",Car.QualityClass.C,2010,40.0f,Car.Status.OUT_OF_STOCK);
        //When
        boolean result =carDao.create(beforeUpdate);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
        carDao.update(afterUpdate);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
        Car carFromDb = carDao.read(1);
        //Then
        Assert.assertEquals(afterUpdate,carFromDb);
    }



}
