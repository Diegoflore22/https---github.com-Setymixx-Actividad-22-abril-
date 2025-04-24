import javax.swing.*;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Crear el gestor de tareas y agregar observador
        GestorDeTareas gestor = new GestorDeTareas();
        gestor.agregarObserver(new ObservadorConsola());

        // Configurar fechas de ejemplo
        Calendar cal = Calendar.getInstance();
        Date hoy = cal.getTime();
        
        cal.add(Calendar.DAY_OF_MONTH, 3);
        Date en3Dias = cal.getTime();
        
        cal.add(Calendar.DAY_OF_MONTH, -5);
        Date hace2Dias = cal.getTime();

        // Agregar tareas de ejemplo con diferentes estados
        Tarea tarea1 = new Tarea("Terminar informe", 2, hoy);
        Tarea tarea2 = new Tarea("Revisar código", 1, en3Dias);
        Tarea tarea3 = new Tarea("Preparar presentación", 3, hace2Dias);
        
        gestor.agregarTarea(tarea1);
        gestor.agregarTarea(tarea2);
        gestor.agregarTarea(tarea3);

        // Cambiar estados de algunas tareas
        gestor.cambiarEstado(tarea1, 0); // En progreso
        gestor.cambiarEstado(tarea3, 2); // Terminada

        // Mostrar tareas sin ordenar
        System.out.println("=== Tareas sin ordenar ===");
        for (Tarea t : gestor.getTareas()) {
            System.out.println(t);
        }

        // Ordenar por prioridad y mostrar
        gestor.setEstrategia(new OrdenarPorPrioridad());
        gestor.ordenarTareas();
        
        System.out.println("\n=== Tareas ordenadas por prioridad ===");
        for (Tarea t : gestor.getTareas()) {
            System.out.println(t);
        }

        // Ordenar por estado y mostrar
        gestor.setEstrategia(new OrdenarPorEstado());
        gestor.ordenarTareas();
        
        System.out.println("\n=== Tareas ordenadas por estado ===");
        for (Tarea t : gestor.getTareas()) {
            System.out.println(t);
        }

        // Mostrar interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            InterfazGrafica gui = new InterfazGrafica(gestor);
            gui.setVisible(true);
            
            // Centrar la ventana
            gui.setLocationRelativeTo(null);
            
            // Mostrar mensaje de bienvenida
            JOptionPane.showMessageDialog(gui, 
                "Sistema de Gestión de Tareas\n\n" +
                "Estados:\n" +
                "0 - En progreso\n" +
                "1 - Por hacer\n" +
                "2 - Terminada", 
                "Bienvenido", 
                JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
