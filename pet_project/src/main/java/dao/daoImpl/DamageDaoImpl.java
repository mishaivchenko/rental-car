package dao.daoImpl;

import entity.Damage;
import dao.ConnectionPool;
import dao.Dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DamageDaoImpl implements Dao<Damage> {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final String CREATE = "INSERT INTO damages (description, sum, isPaid, orderId) VALUES (?, ?, ?,?)";
    private final String READ = "SELECT * FROM damages WHERE damageId = ?";
    private final String READ_ALL = "SELECT * FROM damages";
    private final String UPDATE = "UPDATE damages SET description = ?, sum = ?, isPaid = ? WHERE  damageId = ? ";
    private final String DELETE = "DELETE FROM damages WHERE damageId= ?";
    private final String READ_BY_DAMAGE_ID = "SELECT * FROM damages WHERE orderId=?";

    @Override
    public boolean create(Damage element) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, element.getDescription());
            ps.setFloat(2, element.getSum());
            ps.setBoolean(3, element.isPaid());
            ps.setInt(4,element.getOrderId());
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
    public Damage read(int id) {
        Damage damage = getDamage(id, READ);
        if (damage != null) return damage;
        return null;
    }

    private Damage getDamage(int id, String read) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(read)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Damage damage = new Damage(resultSet.getInt("damageId"),
                            resultSet.getInt("orderId"),
                            resultSet.getString("description"),
                            resultSet.getLong("sum"),
                            resultSet.getBoolean("isPaid"));
                    return damage;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Damage getDamageByOrderId(int id) {
        Damage damage = getDamage(id, READ_BY_DAMAGE_ID);
        if (damage != null) return damage;
        return null;
    }


        @Override
    public List<Damage> read() {
            List<Damage> damageList = new ArrayList<>();

            try (Connection connection = connectionPool.getConnection(); Statement s = connection.createStatement()) {
                try (ResultSet resultSet = s.executeQuery(READ_ALL)) {
                    while (resultSet.next()) {
                        damageList.add(new Damage(resultSet.getInt("damageId"),
                                resultSet.getInt("orderId"),
                                resultSet.getString("description"),
                                resultSet.getLong("sum"),
                                resultSet.getBoolean("isPaid")));
                    }
                    return damageList;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
    public boolean update(Damage element) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, element.getDescription());
            ps.setFloat(2, element.getSum());
            ps.setBoolean(3, element.isPaid());
            ps.setInt(4,element.getId());
                ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


        @Override
    public boolean delete(int id) {

            try(Connection connection = connectionPool.getConnection();PreparedStatement ps = connection.prepareStatement(DELETE)) {
                ps.setInt(1,id);
                ps.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }
}
