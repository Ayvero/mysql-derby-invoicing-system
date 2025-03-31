package factory;
import implementaciones.*;
import interfaces.ClienteDAO;
import interfaces.FacturaDAO;
import interfaces.Factura_ProductoDAO;
import interfaces.ProductoDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOfactory {
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override

    public ClienteDAO getClienteDAO() {
        try {
            return new MySQLClienteDAO(getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public ProductoDAO getProductoDAO() {
        try {
            return new MySQLProductoDAO(getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        try {
            return new MySQLFacturaDAO(getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Factura_ProductoDAO getFactura_ProductoDAO() {
        try {
            return new MySQLFactura_ProductoDAO(getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}