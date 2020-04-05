package DataSource;

import java.beans.*;
import java.lang.reflect.*;
import java.sql.*;
import java.sql.Statement;
import java.util.*;
import java.util.Date;

import Connection.DBConnection;

public class TableGateway<T> {
    public final Class<T> type;

    public TableGateway() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | SQLException | IllegalAccessException | IntrospectionException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("\"");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append("\"");
        return sb.toString();
    }

    public List<T> selectAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = DBConnection.connect();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return createObjects(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(resultSet);
            DBConnection.close(statement);
            DBConnection.close(connection);
        }
        return null;
    }

    protected String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("\"");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append("\"");
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = DBConnection.connect();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(resultSet);
            DBConnection.close(statement);
            DBConnection.close(connection);
        }
        return null;
    }

    protected String createUpdateQuery(String col, String cond) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append("\"");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append("\"");
        sb.append(" SET ");
        sb.append(col + "=" + "?");
        sb.append(" WHERE ");
        sb.append(cond + "=" + "?");
        return sb.toString();
    }

    public void update(String col, String cond, String val1, int val2) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(col, cond);
        try {
            connection = DBConnection.connect();
            statement = connection.prepareStatement(query);
            statement.setString(1, val1);
            statement.setInt(2, val2);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(statement);
            DBConnection.close(connection);
        }
    }

    protected String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append("\"");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append("\"");
        sb.append(" WHERE ");
        sb.append(field + "=?");
        return sb.toString();
    }

    public void delete(int value) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery("id");
        try {
            connection = DBConnection.connect();
            statement = connection.prepareStatement(query);
            statement.setInt(1, value);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(statement);
            DBConnection.close(connection);
        }
    }
}
