package vyrvykhvost.com;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExpressionJDBC {
    private static final Connection CONNECTION = JDBCConfig.getConnection();
    private final static int indexId = 1;
    private final static int indexBody = 2;
    private final static int indexValue = 3;



    public List<Expression> getAll() {
        final List<Expression> result = new ArrayList<>();
        try (final Statement statement = CONNECTION.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM expressions");
            while (resultSet.next()) {
                result.add(new Expression(
                                resultSet.getString(indexId),
                                resultSet.getString(indexBody),
                                resultSet.getDouble(indexValue)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    public  void create(Expression expression) {
        String uuid = UUID.randomUUID().toString();
        String format = "INSERT INTO expressions (id, body, value) VALUES ('%s', '%s', %f)";
        String query = String.format(format, uuid, expression.getBody(), expression.getValue());
        try (final Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public  void update(Expression expression) {
        String format = "UPDATE expressions SET body = '%s', value = %f WHERE id = '%s'";
        String query = String.format(format, expression.getBody(), expression.getValue(), expression.getId());
        try (final Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public  void delete(String id) {
        String format = "DELETE FROM expressions WHERE id = '%s'";
        String query = String.format(format, id);
        try (final Statement statement = CONNECTION.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public  List<Expression> findByValue(String value) {
        List<Expression> result = new ArrayList<>();
        try (final Statement statement = CONNECTION.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM expressions WHERE value = ? ");
            while (resultSet.next()) {
                result.add(new Expression(
                                resultSet.getString(indexBody),
                                resultSet.getString(indexId),
                                resultSet.getDouble(indexValue)
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}


