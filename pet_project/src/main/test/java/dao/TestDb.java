package java.dao;

import config.PropertiesManager;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.h2.tools.RunScript;
import org.h2.tools.Server;
import org.omg.CORBA.portable.ApplicationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestDb {
    private static Server server;
    private static Database database;
    private static Liquibase liquibase;
    public static void prepareDb(Properties properties) throws SQLException, LiquibaseException {
        try {
            server = Server.createTcpServer("-tcpAllowOthers").start();
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        Connection connection = getConnection(properties);

         database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
                new JdbcConnection(connection));
        liquibase = new Liquibase("db_liquibase/db-changelog-master.xml",
                new ClassLoaderResourceAccessor(), database);
        liquibase.update("public");

    }

    public static void stopDb() {
        if (server != null) {
           server.stop();
        }
    }

    public static void dropDb() throws DatabaseException, LockException {
        liquibase.dropAll();
    }

    public static Connection getConnection(Properties properties) throws SQLException {
        try {
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


}