import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date; 

public class InterfazGrafica extends JFrame {
    private GestorDeTareas gestor; // Referencia al gestor de tareas.
    private DefaultListModel<String> listModel = new DefaultListModel<>(); // Modelo de lista para mostrar tareas.
    private JList<String> listaTareas = new JList<>(listModel); // Componente para mostrar tareas.

    public InterfazGrafica(GestorDeTareas gestor) {
        this.gestor = gestor;
        configurarUI(); // Configura la interfaz de usuario.
        actualizarLista(); // Actualiza la lista con las tareas actuales.
    }

    // Configura la interfaz gráfica.
    private void configurarUI() {
        setTitle("Gestor de Tareas");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de lista que contiene las tareas.
        add(new JScrollPane(listaTareas), BorderLayout.CENTER);

        // Panel de botones para interactuar con las tareas.
        JPanel panelBotones = new JPanel(new GridLayout(1, 5)); // Añadimos 5 botones en lugar de 4.

        // Botón para ordenar por prioridad.
        JButton btnPrioridad = new JButton("Ordenar por Prioridad");
        btnPrioridad.addActionListener(e -> {
            gestor.setEstrategia(new OrdenarPorPrioridad());
            gestor.ordenarTareas();
            actualizarLista();
        });

        // Botón para ordenar por fecha.
        JButton btnFecha = new JButton("Ordenar por Fecha");
        btnFecha.addActionListener(e -> {
            gestor.setEstrategia(new OrdenarPorFecha());
            gestor.ordenarTareas();
            actualizarLista();
        });

        // Botón para ordenar por estado.
        JButton btnEstado = new JButton("Ordenar por Estado");
        btnEstado.addActionListener(e -> {
            gestor.setEstrategia(new OrdenarPorEstado());
            gestor.ordenarTareas();
            actualizarLista();
        });

        // Botón para cambiar el estado de una tarea.
        JButton btnCambiarEstado = new JButton("Cambiar Estado");
        btnCambiarEstado.addActionListener(this::cambiarEstadoTarea);

        // Botón para agregar una nueva tarea.
        JButton btnAgregarTarea = new JButton("Agregar Tarea");
        btnAgregarTarea.addActionListener(this::agregarTarea);

        panelBotones.add(btnPrioridad);
        panelBotones.add(btnFecha);
        panelBotones.add(btnEstado);
        panelBotones.add(btnCambiarEstado);
        panelBotones.add(btnAgregarTarea); // Agregamos el botón de agregar tarea.

        add(panelBotones, BorderLayout.SOUTH); // Agrega el panel de botones.
    }

    // Actualiza la lista de tareas en la interfaz gráfica.
    private void actualizarLista() {
        listModel.clear();
        for (Tarea tarea : gestor.getTareas()) {
            listModel.addElement(tarea.toString()); // Muestra las tareas en el modelo de lista.
        }
    }

    // Cambia el estado de la tarea seleccionada en la lista.
    private void cambiarEstadoTarea(ActionEvent e) {
        int indice = listaTareas.getSelectedIndex(); // Obtiene el índice de la tarea seleccionada.
        if (indice != -1) {
            Tarea tarea = gestor.getTareas().get(indice); // Obtiene la tarea seleccionada.
            
            // Opciones de estado para la tarea.
            String[] opciones = {"En progreso (0)", "Por hacer (1)", "Terminada (2)"};
            
            // Solicita al usuario que seleccione un nuevo estado para la tarea.
            int seleccion = JOptionPane.showOptionDialog(this,
                    "Seleccione nuevo estado para '" + tarea.getNombre() + "':",
                    "Cambiar Estado",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[tarea.getEstado()]);
            
            if (seleccion != JOptionPane.CLOSED_OPTION) {
                gestor.cambiarEstado(tarea, seleccion); // Cambia el estado de la tarea.
                actualizarLista(); // Actualiza la lista de tareas.
            }
        }
    }

    // Método para agregar una nueva tarea.
    private void agregarTarea(ActionEvent e) {
        // Pedimos al usuario que ingrese los datos de la tarea.
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la tarea:");
        if (nombre != null && !nombre.isEmpty()) {
            // Pedimos la prioridad de la tarea (un número).
            String prioridadStr = JOptionPane.showInputDialog(this, "Ingrese la prioridad (número):");
            int prioridad = Integer.parseInt(prioridadStr != null ? prioridadStr : "0"); // Valor predeterminado 0 si es inválido.
            
            // Pedimos la fecha de entrega (formato: dd/MM/yyyy).
            String fechaStr = JOptionPane.showInputDialog(this, "Ingrese la fecha de entrega (dd/MM/yyyy):");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaEntrega = null;
            try {
                fechaEntrega = sdf.parse(fechaStr); // Convierte el string en Date.
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // Creamos la nueva tarea y la agregamos al gestor.
            if (fechaEntrega != null) {
                Tarea nuevaTarea = new Tarea(nombre, prioridad, fechaEntrega);
                gestor.agregarTarea(nuevaTarea); // Agrega la tarea al gestor.
                actualizarLista(); // Actualiza la lista en la interfaz gráfica.
            }
        }
    }
}
