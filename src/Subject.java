public interface Subject {
    // Método para agregar un observador que recibirá notificaciones.
    void agregarObserver(Observer observer);
    
    // Método para notificar a todos los observadores con un mensaje.
    void notificar(String mensaje);
}
