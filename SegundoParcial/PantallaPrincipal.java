package SegundoParcial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipal extends JFrame {
    private JTextField nombreField;
    private JTextField apellidosField;
    private JLabel resultadoLabel;
    private JComboBox<String> puestoBox;
    private JComboBox<String> antiguedadBox;
    private JButton calcularButton;
    private JButton limpiarButton;
    private JButton salirButton; // Botón para salir del programa
    private JButton reiniciarButton; // Botón para volver a la bienvenida
    private JPanel panel;  // Panel principal, convertido a instancia de clase

    public PantallaPrincipal(String nombreUsuario) {
        setTitle("Pantalla principal");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // Declaramos el objeto gbc ANTES de usarlo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Cargar el logo desde el archivo y escalarlo
        ImageIcon logoIcon = new ImageIcon("resources/logo.png"); // Asegúrate de que la ruta sea correcta
        Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Escalar la imagen a 150x150
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(logoLabel, gbc);

        // Después del logo, añadimos el título
        gbc.gridy = 1;  // Movemos el título a la siguiente fila
        JLabel tituloLabel = new JLabel("Heladería Munshi");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(tituloLabel, gbc);

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

        // Campos para ingresar datos
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Bienvenido, " + nombreUsuario), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Nombres:"), gbc);

        nombreField = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(nombreField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Apellidos:"), gbc);

        apellidosField = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(apellidosField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Puesto:"), gbc);

        String[] puestos = {"Atención al cliente", "Logística", "Gerente",/*"Jefe" */ };
        puestoBox = new JComboBox<>(puestos);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(puestoBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Antigüedad (años):"), gbc);

        String[] antiguedades = {"1", "2-6", "7-10", /* "11+" */};
        antiguedadBox = new JComboBox<>(antiguedades);
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(antiguedadBox, gbc);

        // Botones
        calcularButton = new JButton("Calcular Días de Vacaciones");
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularVacaciones();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(calcularButton, gbc);

        limpiarButton = new JButton("Limpiar");
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(limpiarButton, gbc);

        // Resultado
        resultadoLabel = new JLabel("Días de vacaciones: ");
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(resultadoLabel, gbc);

        // Botón para reiniciar la consulta
        reiniciarButton = new JButton("Reiniciar Consulta");
        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Volver a la pantalla de bienvenida
                new bienvenida().setVisible(true);
                dispose(); // Cerrar la ventana actual
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 4;
        panel.add(reiniciarButton, gbc);

        // Botón para salir del programa
        salirButton = new JButton("Salir del programa");
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Salir del programa
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(salirButton, gbc);

        add(panel);

        // Acciones para cambiar el color de fondo y texto
        oscuroItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.DARK_GRAY);
                cambiarColorLetra(panel, Color.WHITE);
                cambiarFondoComponentes(Color.DARK_GRAY, Color.WHITE);
            }
        });

        blancoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.WHITE);
                cambiarColorLetra(panel, Color.BLACK);
                cambiarFondoComponentes(Color.WHITE, Color.BLACK);
            }
        });

        amarilloItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.YELLOW);
                cambiarColorLetra(panel, Color.BLACK);
                cambiarFondoComponentes(Color.YELLOW, Color.BLACK);
            }
        });
    }

    // Método para cambiar el color de la letra de todos los componentes
    private void cambiarColorLetra(Container container, Color color) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel || component instanceof JButton || component instanceof JTextField || component instanceof JComboBox) {
                component.setForeground(color);
            }
            if (component instanceof Container) {
                cambiarColorLetra((Container) component, color);
            }
        }
    }

    // Método para cambiar el fondo de botones, JTextField, JComboBox, etc.
    private void cambiarFondoComponentes(Color fondo, Color texto) {
        nombreField.setBackground(fondo);
        nombreField.setForeground(texto);
        apellidosField.setBackground(fondo);
        apellidosField.setForeground(texto);
        puestoBox.setBackground(fondo);
        puestoBox.setForeground(texto);
        antiguedadBox.setBackground(fondo);
        antiguedadBox.setForeground(texto);
        calcularButton.setBackground(fondo);
        calcularButton.setForeground(texto);
        limpiarButton.setBackground(fondo);
        limpiarButton.setForeground(texto);
        reiniciarButton.setBackground(fondo);
        reiniciarButton.setForeground(texto);
        salirButton.setBackground(fondo);
        salirButton.setForeground(texto);
    }

    private void calcularVacaciones() {
        String puesto = (String) puestoBox.getSelectedItem();
        String antiguedad = (String) antiguedadBox.getSelectedItem();
        int diasVacaciones = 0;

        // Cálculo de días de vacaciones
        switch (puesto) {
            case "Atención al cliente":
                if (antiguedad.equals("1")) {
                    diasVacaciones = 6;
                } else if (antiguedad.equals("2-6")) {
                    diasVacaciones = 14;
                } else if (antiguedad.equals("7+")) {
                    diasVacaciones = 18;
                } 
                /*else if (antiguedad.equals("11+")) {
                    diasVacaciones = 22;
                }*/
                break;
            case "Logística":
                if (antiguedad.equals("1")) {
                    diasVacaciones = 7;
                } else if (antiguedad.equals("2-6")) {
                    diasVacaciones = 15;
                } else if (antiguedad.equals("7+")) {
                    diasVacaciones = 22;
                } /*else if (antiguedad.equals("11+")) {
                    diasVacaciones = 22;
                }*/
                break;
            case "Gerente":
                if (antiguedad.equals("1")) {
                    diasVacaciones = 10;
                } else if (antiguedad.equals("2-6")) {
                    diasVacaciones = 20;
                } else if (antiguedad.equals("7+")) {
                    diasVacaciones = 30;
                } /*else if (antiguedad.equals("11+")) {
                    diasVacaciones = 30;
                }*/
                break;
            /*case "Jefe":
                if (antiguedad.equals("1")) {
                    diasVacaciones = 20;
                } else if (antiguedad.equals("2-6")) {
                    diasVacaciones = 40;
                } else if (antiguedad.equals("7-10")) {
                    diasVacaciones = 50;
                } else if (antiguedad.equals("11+")) {
                    diasVacaciones = 60;
                }
                break;*/
        }

        resultadoLabel.setText("Días de vacaciones: " + diasVacaciones);
    }

    private void limpiarCampos() {
        nombreField.setText("");
        apellidosField.setText("");
        puestoBox.setSelectedIndex(0);
        antiguedadBox.setSelectedIndex(0);
        resultadoLabel.setText("Días de vacaciones: ");
    }

    public static void main(String[] args) {
        new PantallaPrincipal("Usuario").setVisible(true);
    }
}
