package services;
import DTO.ClienteDTO;
import DTO.ProductoDTO;
import dbHelper.MySQLhelper;
import entidades.Cliente;
import entidades.Factura;
import entidades.Factura_Producto;
import entidades.Producto;
import implementaciones.DerbyClienteDAO;
import interfaces.*;
import java.util.List;

public class Service {
    private ProductoDAO productoDAO;
    private ClienteDAO clienteDAO;
    private FacturaDAO facturaDAO;
    private Factura_ProductoDAO facturaProductoDAO;


    public Service(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public Service(ClienteDAO clienteDAO) {this.clienteDAO = clienteDAO;}

    public Service(ProductoDAO productoDAO, ClienteDAO clienteDAO, FacturaDAO facturaDAO, Factura_ProductoDAO facturaProductoDAO) {
        this.productoDAO = productoDAO;
        this.clienteDAO = clienteDAO;
        this.facturaDAO = facturaDAO;
        this.facturaProductoDAO = facturaProductoDAO;
    }

    //maneja tabla PRODUCTO
    public void addProducto(Producto p) {
        productoDAO.addProducto(p);
    }
    public List<Producto> getAllProductos() {

        return productoDAO.getAllProductos();
    }

    //maneja tabla CLIENTE
    public void addCliente(Cliente c) {

        clienteDAO.addCliente(c);
    }
    public List<Cliente> getAllClientes() {

        return clienteDAO.getAllClientes();
    }

    //maneja tabla FACTURA
    public void addFactura(Factura f) {

       facturaDAO.addFactura(f);
    }
    public List<Factura> getAllFacturas() {

        return facturaDAO.getAllFacturas();
    }

    //maneja tabla FACTURA_PRODUCTO
    public void addFactura_prod(Factura_Producto fp) {

       facturaProductoDAO.addFacturaProducto(fp);
   }
    public List<Factura_Producto> getAllFacturasProductos() {

        return facturaProductoDAO.getAllFacturasProductos();

    }


    //PUNTO 3 Integrador1
    public ProductoDTO getProductoConMayorRecaudacion() {

        return productoDAO.getProductoConMayorRecaudacion();
    }

    //PUNTO 4 Integrador1
    public List<ClienteDTO> getClientesPorFacturacion(){

        return clienteDAO.getClientesPorFacturacion();
    }


}

