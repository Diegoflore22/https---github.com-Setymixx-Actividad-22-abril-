import java.util.List;
import java.util.Comparator;

public class OrdenarPorFecha implements EstrategiaOrdenamiento {
    // Método que ordena las tareas según la fecha de entrega.
    @Override
    public void ordenar(List<Tarea> tareas) {
        tareas.sort(Comparator.comparing(Tarea::getFechaEntrega)); // Ordena las tareas por la fecha de entrega.
    }
}
