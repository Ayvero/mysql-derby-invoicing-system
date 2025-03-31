package factory;

import interfaces.ClienteDAO;
import interfaces.FacturaDAO;
import interfaces.Factura_ProductoDAO;
import interfaces.ProductoDAO;



public abstract class DAOfactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;


    public abstract ClienteDAO getClienteDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract Factura_ProductoDAO getFactura_ProductoDAO();

    public static DAOfactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC:
                return new MySQLDAOFactory();
            case DERBY_JDBC:
                return new DerbyDAOFactory();

            default:
                return null;
        }
    }
}
