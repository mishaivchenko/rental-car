package dao.daoImpl;

import entity.Car;
import dao.ConnectionPool;
import dao.Dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements Dao<Car> {
    ConnectionPool connectionPool =null;
    final String CREATE = "INSERT INTO cars (carName, model, qualityClass, year, rentPrice,status) VALUES (?, ?, ?, ?, ?,?)";
    final String READ = "SELECT * FROM cars WHERE carId = ?";
    final String READ_ALL = "SELECT * FROM cars";
    final String UPDATE = "UPDATE cars SET carName = ?,model = ?, qualityClass = ?, year = ?, rentPrice = ?, status=? WHERE  carId = ? ";
    final String DELETE = "DELETE FROM cars WHERE carId= ?";
    final String READ_ALL_BY_PARAM = "SELECT * FROM cars WHERE %s = ?";

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean create(Car element) {

        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, element.getCarName());
            ps.setString(2, element.getModel());
            ps.setString(3, String.valueOf(element.getQualityClass()));
            ps.setInt(4, element.getYear());
            ps.setFloat(5, element.getRentPrice());
            ps.setString(6,String.valueOf(element.getStatus()));
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    element.setId(generatedKeys.getInt(1));
                }
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Car read(int id) {
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(READ)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Car car = new Car(resultSet.getInt("carId"),
                            resultSet.getString("carName")
                            , resultSet.getString("model"),
                            Car.QualityClass.valueOf(resultSet.getString("qualityClass")),
                            resultSet.getInt("year"),
                            resultSet.getFloat("rentPrice"),
                            Car.Status.valueOf(resultSet.getString("status")));
                    return car;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> read() {
        List<Car> carList = new ArrayList<>();
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); Statement s = connection.createStatement()) {
            try (ResultSet resultSet = s.executeQuery(READ_ALL)) {
                while (resultSet.next()) {
                    carList.add(new Car(resultSet.getInt("carId"),
                            resultSet.getString("carName"),
                            resultSet.getString("model"),
                            Car.QualityClass.valueOf(resultSet.getString("qualityClass")),
                            resultSet.getInt("year"),
                            resultSet.getFloat("rentPrice"),
                            Car.Status.valueOf(resultSet.getString("status"))));
                }
            }
            return carList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Car element) {
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, element.getCarName());
            ps.setString(2, element.getModel());
            ps.setString(3, String.valueOf(element.getQualityClass()));
            ps.setInt(4, element.getYear());
            ps.setFloat(5, element.getRentPrice());
            ps.setInt(7, element.getId());
            ps.setString(6,String.valueOf(element.getStatus()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Car> getCarsByParameter(String param, String value) {
        List<Car> carListWithParam = new ArrayList<>();
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(String.format(READ_ALL_BY_PARAM, param))) {
            ps.setString(1, value);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    carListWithParam.add(new Car(resultSet.getInt("carId"),
                            resultSet.getString("carName"),
                            resultSet.getString("model"),
                            Car.QualityClass.valueOf(resultSet.getString("qualityClass")),
                            resultSet.getInt("year"),
                            resultSet.getFloat("rentPrice"),
                            Car.Status.valueOf(resultSet.getString("status"))));
                }
            }
            return carListWithParam;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
