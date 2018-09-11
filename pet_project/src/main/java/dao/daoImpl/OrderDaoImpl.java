package dao.daoImpl;

import entity.OrderEntity;
import dao.ConnectionPool;
import dao.Dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements Dao<OrderEntity> {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final String CREATE = "INSERT INTO orders ( userId, carId, status, leaseTerm, driver,customerName,customerSurname,sum,carModelYear) VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
    private final String READ = "SELECT * FROM orders WHERE orderId = ?";
    private final String READ_ALL = "SELECT * FROM orders";
    private final String UPDATE = "UPDATE orders SET userId = ?, carId = ?, damageId = ?, status = ?, date = ?, leaseTerm = ?, driver = ?  WHERE  orderId = ? ";
    private final String DELETE = "DELETE FROM orders WHERE orderId= ?";
    private final String READ_ALL_BY_PARAM = "SELECT * FROM orders WHERE %s = ?";
    private final String READ_ALL_BY_ID = "SELECT * FROM orders WHERE userId = ?";
    private final String UPDATE_PARAM = "UPDATE orders SET %s= ? WHERE orderId = ?";
    private final String UPDATE_SUM = "UPDATE orders SET sum = ? WHERE orderId = ?";
    private final String UPDATE_CRASH = "UPDATE orders SET damageId = ? WHERE orderId =?";
    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
    @Override
    public boolean create(OrderEntity element) {
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,element.getUserId());
            ps.setInt(2,element.getCarId());
          //  ps.setInt(3,element.getDamageId());
            ps.setString(3,String.valueOf(element.getStatus()));
            ps.setInt(4,element.getLeaseTerm());
            ps.setBoolean(5,element.isDriver());
            ps.setString(6,element.getCustomerName());
            ps.setString(7,element.getCustomerSurname());
            ps.setFloat(8,element.getSum());
            ps.setString(9,element.getCarModelYear());
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    element.setOrderId(generatedKeys.getInt(1));
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
    public OrderEntity read(int id) {
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(READ)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    OrderEntity order = new OrderEntity(resultSet.getInt("orderId"),
                            resultSet.getInt("userId"),
                            resultSet.getInt("carId"),
                            resultSet.getInt("damageId"),
                            OrderEntity.Status.valueOf(resultSet.getString("status")),
                            resultSet.getDate("date"),
                            resultSet.getInt("leaseTerm"),
                            resultSet.getBoolean("driver"),
                            resultSet.getString("customerName"),
                            resultSet.getString("customerSurname"),
                            resultSet.getFloat("sum"),
                            resultSet.getString("carModelYear"));
                    String rejectReason = resultSet.getString("rejectionReason");
                        if(rejectReason!=null){
                            order.setRejectionReason(rejectReason);
                        }
                    return order;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderEntity> read() {
        List<OrderEntity> orderList = new ArrayList<>();
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); Statement s = connection.createStatement()) {
            try (ResultSet resultSet = s.executeQuery(READ_ALL)) {
                while (resultSet.next()) {
                    orderList.add(new OrderEntity(resultSet.getInt("orderId"),
                            resultSet.getInt("userId"),
                            resultSet.getInt("carId"),
                            resultSet.getInt("damageId"),
                            OrderEntity.Status.valueOf(resultSet.getString("status")),
                            resultSet.getDate("date"),
                            resultSet.getInt("leaseTerm"),
                            resultSet.getBoolean("driver"),
                            resultSet.getString("customerName"),
                            resultSet.getString("customerSurname"),
                            resultSet.getFloat("sum"),
                            resultSet.getString("carModelYear")));
                }
            } return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(OrderEntity element) {
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection =connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,element.getUserId());
            ps.setInt(2,element.getCarId());
            ps.setInt(3,element.getDamageId());
            ps.setString(4,String.valueOf(element.getStatus()));
            ps.setDate(5, (Date) element.getDate());
            ps.setInt(6,element.getLeaseTerm());
            ps.setBoolean(7,element.isDriver());
            ps.setInt(8,element.getOrderId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateParamById(String param,String value, int id) {
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(String.format(UPDATE_PARAM, param), PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1,value);
            ps.setInt(2,id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateSumById(float sum, int id){

        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_SUM, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setFloat(1,sum);
            ps.setInt(2,id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCrashById(int crashId, int id){
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_CRASH, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,crashId);
            ps.setInt(2,id);
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
        try(Connection connection = connectionPool.getConnection();PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1,id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<OrderEntity> getOrdersByParameter(String param, String value) {
        List<OrderEntity> orderEntityList  = new ArrayList<>();
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(String.format(READ_ALL_BY_PARAM, param))) {
            ps.setString(1, value);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    orderEntityList.add(new OrderEntity(resultSet.getInt("orderId"),
                            resultSet.getInt("userId"),
                            resultSet.getInt("carId"),
                            resultSet.getInt("damageId"),
                            OrderEntity.Status.valueOf(resultSet.getString("status")),
                            resultSet.getDate("date"),
                            resultSet.getInt("leaseTerm"),
                            resultSet.getBoolean("driver"),
                            resultSet.getString("customerName"),
                            resultSet.getString("customerSurname"),
                            resultSet.getFloat("sum"),
                            resultSet.getString("carModelYear")));

                }
            }
            return orderEntityList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<OrderEntity> getOrdersByUserId(int id) {
        List<OrderEntity> orderEntityList = new ArrayList<>();
        if(connectionPool==null) connectionPool = ConnectionPool.getInstance();
        try (Connection connection =connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(READ_ALL_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    orderEntityList.add(new OrderEntity(resultSet.getInt("orderId"),
                            resultSet.getInt("userId"),
                            resultSet.getInt("carId"),
                            resultSet.getInt("damageId"),
                            OrderEntity.Status.valueOf(resultSet.getString("status")),
                            resultSet.getDate("date"),
                            resultSet.getInt("leaseTerm"),
                            resultSet.getBoolean("driver"),
                            resultSet.getString("customerName"),
                            resultSet.getString("customerSurname"),
                            resultSet.getFloat("sum"),
                            resultSet.getString("carModelYear")));
                }
            }
            return orderEntityList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
