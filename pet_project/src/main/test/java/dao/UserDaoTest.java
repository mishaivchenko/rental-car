package java.dao;

import dao.ConnectionPool;
import entity.User;
import config.PropertiesManager;
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

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDaoTest {
    TestDb testDb;
    @InjectMocks
    UserDaoImpl userDao;;
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
    public void addUserTest() throws SQLException {
        //Given
        ConnectionPool connectionPooll = mock(ConnectionPool.class);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));

        userDao.setConnectionPool(connectionPooll);
        User user = new User(1,"misha","ivchenko","mishaivchenko","admin","mishaivchenko@gmail.com",User.Role.ADMIN,User.UserStatus.NONBLOCKING);
        //When
        boolean result =userDao.create(user);
        userDao.setConnectionPool(connectionPooll);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
        User userFromDb = userDao.read(1);
        //Then
        Assert.assertTrue(result);
        Assert.assertEquals(user,userFromDb);
    }

    @Test
    public void updateUserTest() throws SQLException {
        //Given
        ConnectionPool connectionPooll = mock(ConnectionPool.class);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
        userDao.setConnectionPool(connectionPooll);
        User beforeUpdate = new User(1,"misha","ivchenko","mishaivchenko","admin","mishaivchenko@gmail.com",User.Role.ADMIN,User.UserStatus.NONBLOCKING);
        User afterUpdate =new User(1,"firstname","lastname","username","username","email.@gmail.com",User.Role.ADMIN,User.UserStatus.NONBLOCKING);
        //When
        boolean result =userDao.create(beforeUpdate);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
        userDao.update(afterUpdate);
        when(connectionPooll.getConnection()).thenReturn(TestDb.getConnection(propertiesManager.getH2Properties()));
        User userFromDb = userDao.read(1);
        //Then
        Assert.assertEquals(afterUpdate,userFromDb);
    }

}
