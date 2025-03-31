package factory;

import implementaciones.DerbyClienteDAO;
import implementaciones.DerbyFacturaDAO;
import implementaciones.DerbyFactura_ProductoDAO;
import implementaciones.DerbyProductoDAO;
import interfaces.ClienteDAO;
import interfaces.FacturaDAO;
import interfaces.Factura_ProductoDAO;
import interfaces.ProductoDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyDAOFactory extends DAOfactory {
    private static final String URL = "jdbc:derby:myDatabase;create=true";
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection()  throws SQLException {
        return DriverManager.getConnection(URL);
    }

    @Override

    public ClienteDAO getClienteDAO() {
        try {
            return new DerbyClienteDAO(getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public ProductoDAO getProductoDAO() {
        try {
            return new DerbyProductoDAO(getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        try {
            return new DerbyFacturaDAO(getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Factura_ProductoDAO getFactura_ProductoDAO() {
        try {
            return new DerbyFactura_ProductoDAO(getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
