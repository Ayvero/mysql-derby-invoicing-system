package DTO;

public class ProductoDTO {

    private int idProducto;
    private String nombre;
    private float totalRecaudacion;

    public ProductoDTO(int idProducto, String nombre, float totalRecaudacion) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.totalRecaudacion = totalRecaudacion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getTotalRecaudacion() {
        return totalRecaudacion;
    }

    public void setTotalRecaudadacion(float totalRecaudacion) {
        this.totalRecaudacion = totalRecaudacion;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", totalRecaudacion=" + totalRecaudacion +
                '}';
    }

}
