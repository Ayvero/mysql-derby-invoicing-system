package dbHelper;
import entidades.Cliente;
import entidades.Factura;
import entidades.Factura_Producto;
import entidades.Producto;
import factory.MySQLDAOFactory;
import interfaces.ClienteDAO;
import interfaces.FacturaDAO;
import interfaces.Factura_ProductoDAO;
import interfaces.ProductoDAO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLhelper {
    private MySQLDAOFactory daoFactory;
    private ProductoDAO productoDAO;
    private ClienteDAO clienteDAO;
    private FacturaDAO facturaDAO;
    private Factura_ProductoDAO facturaProductoDAO;

    public MySQLhelper(MySQLDAOFactory daoFactory) {
        this.productoDAO = daoFactory.getProductoDAO();
        this.clienteDAO = daoFactory.getClienteDAO();
        this.facturaDAO = daoFactory.getFacturaDAO();
        this.facturaProductoDAO = daoFactory.getFactura_ProductoDAO();
        this.daoFactory = daoFactory;
    }
    public Connection getConnection() {
        try {
            return daoFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Métodos para crear tablas
    public void createTableCliente() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Cliente (idCliente INT, nombre VARCHAR (500), email VARCHAR (150), PRIMARY KEY(idCliente))";
        executeStatement(createTableSQL, "Tabla Cliente creada con éxito en MySQL.");
    }

    public void createTableFacturaProducto() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Factura_Producto (idFactura INT, idProducto INT, cantidad INT, PRIMARY KEY(idFactura, idProducto))";
        executeStatement(createTableSQL, "Tabla Factura_Producto creada con éxito en MySQL.");
    }

    public void createTableFactura() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Factura (idFactura INT, idCliente INT, PRIMARY KEY(idFactura))";
        executeStatement(createTableSQL, "Tabla Factura creada con éxito en MySQL.");
    }

    public void createTableProducto() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Producto (idProducto INT, nombre VARCHAR (45), valor FLOAT, PRIMARY KEY(idProducto))";
        executeStatement(createTableSQL, "Tabla Producto creada con éxito en MySQL.");
    }

    private void executeStatement(String sql, String successMessage) {
        try (Connection connection = getConnection(); Statement stmt = connection.createStatement()) {
            if (connection == null) {
                System.err.println("Error: La conexión a la base de datos no está inicializada.");
                return;
            }
            stmt.execute(sql);
            System.out.println(successMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar datos desde CSV
    public void loadClientesFromCSV(String csvFilePath) {
        try (FileReader reader = new FileReader(csvFilePath);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("idCliente", "nombre", "email").withIgnoreHeaderCase().withTrim());)
              {
            for (CSVRecord record : parser) {
                int idCliente = Integer.parseInt(record.get("idCliente"));
                String nombre = record.get("nombre");
                String email = record.get("email");
                final var c = new Cliente(idCliente, nombre, email);
                clienteDAO.addCliente(c);
            }
                  System.out.println("Datos de clientes  cargados desde CSV a MySQL.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFacturas_ProductosFromCSV(String csvFilePath) {
        try (FileReader reader = new FileReader(csvFilePath);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("idFactura", "idProducto", "cantidad").withIgnoreHeaderCase().withTrim());
             ) {
            for (CSVRecord record : parser) {
                int idFactura = Integer.parseInt(record.get("idFactura"));
                int idProducto = Integer.parseInt(record.get("idProducto"));
                int cantidad = Integer.parseInt(record.get("cantidad"));
                final var fp = new Factura_Producto(idFactura, idProducto, cantidad);
                facturaProductoDAO.addFacturaProducto(fp);
            }
           System.out.println("Datos de factura_productos  cargados desde CSV a MySQL.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFacturasFromCSV(String csvFilePath) {
        try (FileReader reader = new FileReader(csvFilePath);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("idFactura", "idCliente").withIgnoreHeaderCase().withTrim());
             ) {
            for (CSVRecord record : parser) {
                int idFactura = Integer.parseInt(record.get("idFactura"));
                int idCliente = Integer.parseInt(record.get("idCliente"));
                final var f = new Factura(idFactura, idCliente);
                facturaDAO.addFactura(f);
            }
            System.out.println("Datos de facturas  cargados desde CSV a MySQL.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadProductosFromCSV(String csvFilePath) {
        try (FileReader reader = new FileReader(csvFilePath);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("idProducto", "nombre", "valor").withIgnoreHeaderCase().withTrim());
             ) {
            for (CSVRecord record : parser) {
                int idProducto = Integer.parseInt(record.get("idProducto"));
                String nombre = record.get("nombre");
                float valor = Float.parseFloat(record.get("valor"));
                final var p = new Producto(idProducto, nombre, valor);
                productoDAO.addProducto(p);
            }
            System.out.println("Datos de Productos  cargados desde CSV a MySQL.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}