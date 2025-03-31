package implementaciones;
import DTO.ClienteDTO;
import entidades.Cliente;
import DTO.ClienteDTO;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import interfaces.ClienteDAO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class MySQLClienteDAO implements ClienteDAO {
    private final Connection connection;

    public MySQLClienteDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void addCliente(Cliente cliente) {
        String insertSQL = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setInt(1, cliente.getIdCliente());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getEmail());
            pstmt.executeUpdate();
            System.out.println("Cliente añadido a MySQL.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Cliente> getAllClientes() {
        String selectSQL = "SELECT * FROM Cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(selectSQL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                clientes.add(new Cliente(idCliente, nombre, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    //Punto 4 Integrador1
    public List<ClienteDTO> getClientesPorFacturacion() {
        List<ClienteDTO> clientes = new ArrayList<>();
        String query = "SELECT c.idCliente, c.nombre, SUM(fp.cantidad * p.valor) AS recaudacion " +
                "FROM Cliente c " +
                "JOIN Factura f ON c.idCliente = f.idCliente " +
                "JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                "JOIN Producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY c.idCliente, c.nombre " +
                "ORDER BY recaudacion DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                float recaudacion = rs.getFloat("recaudacion");
                ClienteDTO c = new ClienteDTO(idCliente, nombre, recaudacion);
              //  Cliente c = new Cliente(idCliente, nombre, recaudacion);
                clientes.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    // Método para borrar la tabla
    public void dropTable()  {
        String sql = "DROP TABLE IF EXISTS Cliente";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
