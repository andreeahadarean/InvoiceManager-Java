package DataSource;

import Model.Invoice;
import Connection.DBConnection;
import java.sql.*;
import java.util.Date;

public class InvoiceGateway extends TableGateway<Invoice> {

    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append("\"");
        sb.append("invoice");
        sb.append("\"");
        sb.append("(total, seller, products, \"payDate\", id, duplicate, \"dueDate\", \"remainingDays\") VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        return sb.toString();
    }

    public void insert(int id, String seller, String products, float total, Date dueDate, Date payDate, boolean duplicate, int remainingDays) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();
        try {
            connection = DBConnection.connect();
            statement = connection.prepareStatement(query);
            statement.setFloat(1, total);
            statement.setString(2, seller);
            statement.setString(3, products);
            if(payDate == null) {
                statement.setDate(4, null);
            } else {
                statement.setDate(4, new java.sql.Date(payDate.getTime()));
            }
            statement.setInt(5, id);
            statement.setBoolean(6, duplicate);
            statement.setDate(7, new java.sql.Date(dueDate.getTime()));
            statement.setInt(8, remainingDays);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(statement);
            DBConnection.close(connection);
        }
    }

    public void update(String col, String cond, Date val1, int val2) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(col, cond);
        try {
            connection = DBConnection.connect();
            statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(val1.getTime()));
            statement.setInt(2, val2);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(statement);
            DBConnection.close(connection);
        }
    }
}
