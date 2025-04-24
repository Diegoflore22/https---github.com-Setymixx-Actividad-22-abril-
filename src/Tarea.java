import java.util.Date;

public class Tarea {
    private String nombre; // Nombre de la tarea.
    private int prioridad; // Prioridad de la tarea.
    private Date fechaEntrega; // Fecha de entrega de la tarea.
    private int estado; // Estado de la tarea (0: En progreso, 1: Por hacer, 2: Terminada).

    // Constructor de la tarea, establece valores por defecto.
    public Tarea(String nombre, int prioridad, Date fechaEntrega) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.fechaEntrega = fechaEntrega;
        this.estado = 1; // Por defecto "Por hacer" (1).
    }

    // Métodos getter para acceder a los atributos.
    public String getNombre() { return nombre; }
    public int getPrioridad() { return prioridad; }
    public Date getFechaEntrega() { return fechaEntrega; }
    public int getEstado() { return estado; }
    
    // Método que convierte el estado numérico a texto.
    public String getEstadoComoTexto() {
        switch(estado) {
            case 0: return "En progreso";
            case 1: return "Por hacer";
            case 2: return "Terminada";
            default: return "Desconocido";
        }
    }
    
    // Método para establecer un nuevo estado a la tarea.
    public void setEstado(int nuevoEstado) {
        if (nuevoEstado >= 0 && nuevoEstado <= 2) {
            this.estado = nuevoEstado; // Asigna el nuevo estado si es válido.
        }
    }

    // Método para representar la tarea como un string.
    @Override
    public String toString() {
        return String.format("%s (Prioridad: %d, Fecha: %s, Estado: %s)", 
                nombre, prioridad, fechaEntrega.toString(), getEstadoComoTexto());
    }
}
