import java.util.List;
import java.util.Comparator;

public class OrdenarPorEstado implements EstrategiaOrdenamiento {
    // Método que ordena las tareas según su estado.
    @Override
    public void ordenar(List<Tarea> tareas) {
        tareas.sort(Comparator.comparingInt(Tarea::getEstado)); // Ordena las tareas por estado (0: En progreso, 1: Por hacer, 2: Terminada).
    }
}
