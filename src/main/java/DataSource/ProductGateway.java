package DataSource;

import Model.Product;
import Connection.DBConnection;
import java.sql.*;

public class ProductGateway extends TableGateway<Product>{

    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append("\"");
        sb.append("product");
        sb.append("\"");
        sb.append(" (id, name, price) VALUES (?, ?, ?)");
        return sb.toString();
    }

    public void insert(int id, String name, int price) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();
        try {
            connection = DBConnection.connect();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, price);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(statement);
            DBConnection.close(connection);
        }
    }
}
