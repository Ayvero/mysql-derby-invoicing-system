package interfaces;
import DTO.ProductoDTO;
import entidades.Producto;
import java.util.List;

public interface ProductoDAO {
    void addProducto(Producto p);
    List<Producto> getAllProductos();
    ProductoDTO getProductoConMayorRecaudacion();
    void dropTable();
}
