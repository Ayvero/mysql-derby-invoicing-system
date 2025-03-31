package interfaces;
import entidades.Factura_Producto;
import java.util.List;

public interface Factura_ProductoDAO {
    void addFacturaProducto(Factura_Producto facturaProducto);
    List<Factura_Producto> getAllFacturasProductos();
    void dropTable();
}
