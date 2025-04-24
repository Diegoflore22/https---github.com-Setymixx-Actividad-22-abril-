import java.util.ArrayList;
import java.util.List;

public class GestorDeTareas implements Subject {
    private List<Tarea> tareas = new ArrayList<>(); // Lista que almacena las tareas.
    private EstrategiaOrdenamiento estrategia; // Estrategia para ordenar tareas.
    private List<Observer> observers = new ArrayList<>(); // Lista de observadores que reciben notificaciones.

    // Método para agregar una tarea y notificar a los observadores.
    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea); // Agrega la tarea a la lista.
        notificar("Nueva tarea agregada: " + tarea.getNombre()); // Notifica a los observadores.
    }

    // Método para establecer la estrategia de ordenamiento.
    public void setEstrategia(EstrategiaOrdenamiento estrategia) {
        this.estrategia = estrategia;
    }

    // Método que ordena las tareas usando la estrategia definida.
    public void ordenarTareas() {
        if (estrategia != null) {
            estrategia.ordenar(tareas); // Ordena las tareas usando la estrategia.
            notificar("Tareas ordenadas usando " + estrategia.getClass().getSimpleName()); // Notifica a los observadores.
        }
    }

    // Método para cambiar el estado de una tarea y notificar a los observadores.
    public void cambiarEstado(Tarea tarea, int nuevoEstado) {
        if (nuevoEstado >= 0 && nuevoEstado <= 2) { // Verifica que el estado esté en el rango válido.
            String mensaje = String.format("Tarea '%s' cambió de estado de '%s' a '%s'",
                    tarea.getNombre(), 
                    tarea.getEstadoComoTexto(), 
                    getEstadoComoTexto(nuevoEstado));
            tarea.setEstado(nuevoEstado); // Actualiza el estado de la tarea.
            notificar(mensaje); // Notifica a los observadores.
        }
    }

    // Convierte el valor numérico del estado en texto.
    private String getEstadoComoTexto(int estado) {
        switch(estado) {
            case 0: return "En progreso";
            case 1: return "Por hacer";
            case 2: return "Terminada";
            default: return "Desconocido";
        }
    }

    // Devuelve una copia de la lista de tareas.
    public List<Tarea> getTareas() {
        return new ArrayList<>(tareas);
    }

    // Implementación de agregarObserver de la interfaz Subject.
    @Override
    public void agregarObserver(Observer observer) {
        observers.add(observer); // Agrega un observador a la lista.
    }

    // Notifica a todos los observadores.
    @Override
    public void notificar(String mensaje) {
        for (Observer observer : observers) {
            observer.actualizar(mensaje); // Llama al método actualizar de cada observador.
        }
    }
}
