package interfaces;
import entidades.Factura;
import java.util.List;

public interface FacturaDAO {
    void addFactura(Factura factura);
    List<Factura> getAllFacturas();
    void dropTable();
}
