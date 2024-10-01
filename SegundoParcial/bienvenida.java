package SegundoParcial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bienvenida extends JFrame {
    private JTextField nameField;
    private JButton nextButton;
    private JPanel panel;

    public bienvenida() {
        setTitle("MUNSHI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Icono de la aplicación (favicon)
        Image icon = Toolkit.getDefaultToolkit().getImage("resources/logo.png");
        setIconImage(icon);

        // Crear el menú superior
        JMenuBar menuBar = new JMenuBar();
        JMenu opcionesMenu = new JMenu("Opciones");
        menuBar.add(opcionesMenu);
        setJMenuBar(menuBar);

        // Crear las opciones del submenú
        JMenuItem oscuroItem = new JMenuItem("Fondo Oscuro / Texto Blanco");
        JMenuItem blancoItem = new JMenuItem("Fondo Blanco / Texto Negro");
        JMenuItem amarilloItem = new JMenuItem("Fondo Amarillo / Texto Negro");

        // Añadir las opciones al menú
        opcionesMenu.add(oscuroItem);
        opcionesMenu.add(blancoItem);
        opcionesMenu.add(amarilloItem);

        // Panel principal
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));  // Aumentar a 5 filas para agregar nuevo texto

        // Nombre del negocio y del estudiante
        JLabel negocioLabel = new JLabel("Bienvenido al programa para calcular días de vacaciones", JLabel.CENTER);
        panel.add(negocioLabel);

        // Campo para ingresar el número de trabajador
        nameField = new JTextField();
        panel.add(new JLabel("Ingrese su nombre: ", JLabel.CENTER));
        panel.add(nameField);

        // Agregar nuevo texto debajo del campo de texto
        JLabel nuevoTexto = new JLabel("Kab'Tzin Miguel Angel Velasco Xuc 1990-23-3004 Sección D", JLabel.CENTER);
        panel.add(nuevoTexto);

        // Botón para pasar a la siguiente pantalla
        nextButton = new JButton("Siguiente");
        nextButton.setEnabled(false);  // Deshabilitado hasta que se ingrese un nombre
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!nameField.getText().trim().isEmpty()) {
                    // Pasar a la siguiente pantalla
                    new TerminosYCondiciones(nameField.getText()).setVisible(true);
                    dispose(); // Cerrar la pantalla de bienvenida
                }
            }
        });

        // Habilitar el botón solo si se ingresa un nombre
        nameField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                habilitarBoton();
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                habilitarBoton();
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                habilitarBoton();
            }

            public void habilitarBoton() {
                nextButton.setEnabled(!nameField.getText().trim().isEmpty());
            }
        });

        panel.add(nextButton);
        add(panel);

        // Acciones para cambiar el color de fondo y texto
        oscuroItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.DARK_GRAY);
                cambiarColorLetra(panel, Color.WHITE);
                cambiarFondoComponentes(Color.DARK_GRAY, Color.WHITE);  // Cambiar colores adicionales
            }
        });

        blancoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.WHITE);
                cambiarColorLetra(panel, Color.BLACK);
                cambiarFondoComponentes(Color.WHITE, Color.BLACK);  // Cambiar colores adicionales
            }
        });

        amarilloItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.YELLOW);
                cambiarColorLetra(panel, Color.BLACK);
                cambiarFondoComponentes(Color.YELLOW, Color.BLACK);  // Cambiar colores adicionales
            }
        });
    }

    // Método para cambiar el color de la letra de todos los componentes
    private void cambiarColorLetra(Container container, Color color) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel || component instanceof JButton || component instanceof JTextField) {
                component.setForeground(color);  // Cambiar color del texto
            }
            if (component instanceof Container) {
                cambiarColorLetra((Container) component, color);
            }
        }
    }

    // Método para cambiar el fondo de los componentes como botones y el JTextField
    private void cambiarFondoComponentes(Color fondo, Color texto) {
        nameField.setBackground(fondo);  // Cambiar fondo del campo de texto
        nameField.setForeground(texto);  // Cambiar color del texto del campo de texto
        nextButton.setBackground(fondo);  // Cambiar fondo del botón
        nextButton.setForeground(texto);  // Cambiar texto del botón
    }

    public static void main(String[] args) {
        // Iniciar la pantalla de bienvenida
        new bienvenida().setVisible(true);
    }
}
