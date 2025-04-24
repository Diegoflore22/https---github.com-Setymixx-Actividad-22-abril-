public class ObservadorConsola implements Observer {
    // Método que recibe las notificaciones y las muestra por consola.
    @Override
    public void actualizar(String mensaje) {
        System.out.println("[Notificación] " + mensaje); // Muestra el mensaje en la consola.
    }
}
