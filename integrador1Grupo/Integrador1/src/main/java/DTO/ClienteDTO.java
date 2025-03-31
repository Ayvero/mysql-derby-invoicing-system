package DTO;

public class ClienteDTO {
    private int idCliente;
    private String nombre;
    private float recaudacion;


    public ClienteDTO(int idCliente, String nombre, float recaudacion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.recaudacion = recaudacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public float getRecaudacion() {
        return recaudacion;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", totalRecaudacion=" + recaudacion +
                '}';
    }

}
