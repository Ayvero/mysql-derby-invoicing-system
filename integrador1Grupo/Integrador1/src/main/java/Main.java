import DTO.ClienteDTO;
import DTO.ProductoDTO;
import dbHelper.MySQLhelper;
import entidades.Cliente;
import entidades.Producto;
import factory.DAOfactory;
import factory.MySQLDAOFactory;
import interfaces.ClienteDAO;
import interfaces.FacturaDAO;
import interfaces.Factura_ProductoDAO;
import interfaces.ProductoDAO;
import services.Service;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ruta a los archivos CSV
        String csvProductos = "src/main/java/datasets/productos.csv";
        String csvClientes = "src/main/java/datasets/clientes.csv";
        String csvFacturas = "src/main/java/datasets/facturas.csv";
        String csvFacturaProductos = "src/main/java/datasets/facturas-productos.csv";

        // Seleccionar la base de datos a utilizar (1 para MySQL, 2 para Derby)
        DAOfactory daoFactory = DAOfactory.getDAOFactory(1);

        // Se obtiene los DAOs para cada entidad
        ClienteDAO clienteDAO = daoFactory.getClienteDAO();
        ProductoDAO productoDAO = daoFactory.getProductoDAO();
        FacturaDAO facturaDAO = daoFactory.getFacturaDAO();
        Factura_ProductoDAO factura_productoDAO = daoFactory.getFactura_ProductoDAO();

        //Instancio helper para MySQL
        MySQLhelper helper = new MySQLhelper((MySQLDAOFactory) daoFactory);
        //Instancio helper para Derby
   //   Derbyhelper helper = new Derbyhelper((DerbyDAOFactory) daoFactory);
        helper.createTableProducto();
        helper.createTableCliente();
        helper.createTableFactura();
        helper.createTableFacturaProducto();

        // Carga datos desde los archivos CSV
        helper.loadClientesFromCSV(csvClientes);
        helper.loadFacturasFromCSV(csvFacturas);
        helper.loadProductosFromCSV(csvProductos);
        helper.loadFacturas_ProductosFromCSV(csvFacturaProductos);

        //SERVICIOS
        Service service = new Service(productoDAO, clienteDAO, facturaDAO, factura_productoDAO);

       // PUNTO 3: Obtener el producto con mayor recaudación
        ProductoDTO productoConMayorRecaudacion = service.getProductoConMayorRecaudacion();
        if (productoConMayorRecaudacion != null) {
            System.out.println("PRODUCTO CON MAYOR RECAUDACION:");
            System.out.println("ID: " + productoConMayorRecaudacion.getIdProducto());
            System.out.println("Nombre: " + productoConMayorRecaudacion.getNombre());
            System.out.println("Recaudación: " + productoConMayorRecaudacion.getTotalRecaudacion());
        } else {
            System.out.println("No se encontró ningún producto.");
        }

        // PUNTO 4: Obtener la lista de clientes ordenados por facturación
        List<ClienteDTO> clientes = service.getClientesPorFacturacion();
        System.out.println("LISTADO DE CLIENTES ORDENADO POR FACTURACIÓN:  ");
        for (ClienteDTO cliente : clientes) {
            System.out.printf("ID: %d, Nombre: %s, Recaudación: %.2f%n",
                    cliente.getIdCliente(), cliente.getNombre(), cliente.getRecaudacion());
        }

/*
        // Consulta y muestra los datos de cada una de las tablas
        List<Cliente> clientesss = service.getAllClientes();
        System.out.println("CLIENTES");
        for (Cliente c : clientesss) {
            System.out.println("IdCliente: " + c.getIdCliente() +
                    ", Nombre: " + c.getNombre() +
                    ", Email: " + c.getEmail());

        }

        List<Producto> productos = service.getAllProductos();
        System.out.println("PRODUCTOS /n");
        for (Producto p : productos) {
            System.out.println("IdProducto: " + p.getIdProducto() +
                    ", Nombre: " + p.getNombre() +
                    ", Valor: " + p.getValor());

        }

        List<Factura> facturas = service.getAllFacturas();
        System.out.println("FACTURAS /n");
        for (Factura f : facturas) {
            System.out.println("IdFactura: " + f.getIdFactura() +
                    ", IdCliente: " + f.getIdCliente());

        }
        List<Factura_Producto> facturasp = service.getAllFacturasProductos();
        System.out.println("FACTURASPROD /n");
        for (Factura_Producto f : facturasp) {
            System.out.println("IdFactura: " + f.getIdFactura() +
                    ", IdProducto: " + f.getIdProducto() +
                    "cantidad: " + f.getCantidad());

        }*/

    }

}


