import java.util.List;
import java.util.Comparator;

public class OrdenarPorPrioridad implements EstrategiaOrdenamiento {
    // Método que ordena las tareas según su prioridad.
    @Override
    public void ordenar(List<Tarea> tareas) {
        tareas.sort(Comparator.comparingInt(Tarea::getPrioridad)); // Ordena las tareas por prioridad.
    }
}
