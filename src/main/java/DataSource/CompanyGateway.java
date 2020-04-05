package DataSource;

import Model.Company;
import java.sql.*;
import Connection.DBConnection;

import java.sql.PreparedStatement;

public class CompanyGateway extends TableGateway<Company> {
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append("\"");
        sb.append("company");
        sb.append("\"");
        sb.append(" (id, name, phonenr) VALUES (?, ?, ?)");
        return sb.toString();
    }

    public void insert(int id, String name, String phoneNumber) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();
        try {
            connection = DBConnection.connect();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, phoneNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(statement);
            DBConnection.close(connection);
        }
    }
}
