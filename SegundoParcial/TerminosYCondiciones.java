package SegundoParcial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerminosYCondiciones extends JFrame {
    private JCheckBox aceptarCheckBox;
    private JButton continuarButton;
    private JButton noAceptarButton;
    private JTextArea terminosTextArea;  // Declarar el JTextArea como variable de instancia
    private JPanel buttonPanel;  // Panel de los botones
    private String nombreUsuario;

    public TerminosYCondiciones(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;

        setTitle("Términos y Condiciones");
        setSize(500, 400);
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

        // Usamos GridBagLayout para disposición más flexible
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // Título y mensaje de bienvenida con el nombre del usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JLabel("Usuario: " + nombreUsuario, JLabel.CENTER), gbc);

        // Área de texto con los términos y condiciones
        terminosTextArea = new JTextArea(
            "Al momento de usar este software para calcular los días de vacaciones ACEPTA dar sus datos personales "
            + "como NOMBRE - APELLIDOS - ANTIGÜEDAD LABORAL - PUESTO QUE TIENE A CARGO. Usted al momento de usar "
            + "este software RENUNCIA a responsabilizar al creador Kab'Tzin Miguel Angel Velasco Xuc con número de "
            + "carnet: 1990-23-3004 por malos usos de este programa y toda acción RECAE en el USUARIO."
        );
        terminosTextArea.setWrapStyleWord(true);
        terminosTextArea.setLineWrap(true);
        terminosTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(terminosTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // CheckBox para aceptar términos
        aceptarCheckBox = new JCheckBox("Acepto los términos y condiciones");
        aceptarCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continuarButton.setEnabled(aceptarCheckBox.isSelected());
                noAceptarButton.setEnabled(!aceptarCheckBox.isSelected());
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(aceptarCheckBox, gbc);

        // Botones para continuar o no aceptar
        continuarButton = new JButton("Continuar");
        continuarButton.setEnabled(false);
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aceptarCheckBox.isSelected()) {
                    new PantallaPrincipal(nombreUsuario).setVisible(true);
                    dispose();
                }
            }
        });

        noAceptarButton = new JButton("No Aceptar");
        noAceptarButton.setEnabled(true);
        noAceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new bienvenida().setVisible(true);
                dispose();
            }
        });

        // Añadir los botones
        buttonPanel = new JPanel();  // Crear el panel de los botones
        buttonPanel.add(continuarButton);
        buttonPanel.add(noAceptarButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Acciones para cambiar el color de fondo, texto y botones
        oscuroItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().setBackground(Color.DARK_GRAY);
                cambiarColorLetra(getContentPane(), Color.WHITE);
                cambiarFondoComponentes(Color.DARK_GRAY, Color.WHITE);  // Aplicar color a los botones y checkbox
            }
        });

        blancoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().setBackground(Color.WHITE);
                cambiarColorLetra(getContentPane(), Color.BLACK);
                cambiarFondoComponentes(Color.WHITE, Color.BLACK);  // Aplicar color a los botones y checkbox
            }
        });

        amarilloItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().setBackground(Color.YELLOW);
                cambiarColorLetra(getContentPane(), Color.BLACK);
                cambiarFondoComponentes(Color.YELLOW, Color.BLACK);  // Aplicar color a los botones y checkbox
            }
        });
    }

    // Método para cambiar el color de la letra de todos los componentes
    private void cambiarColorLetra(Container container, Color color) {
        for (Component component : container.getComponents()) {
            if (component instanceof JLabel || component instanceof JButton || component instanceof JCheckBox || component instanceof JTextArea) {
                component.setForeground(color);
            }
            if (component instanceof Container) {
                cambiarColorLetra((Container) component, color);
            }
        }
    }

    // Método para cambiar el fondo de botones, checkbox, JTextArea y panel de botones
    private void cambiarFondoComponentes(Color fondo, Color texto) {
        aceptarCheckBox.setBackground(fondo);
        aceptarCheckBox.setForeground(texto);
        continuarButton.setBackground(fondo);
        continuarButton.setForeground(texto);
        noAceptarButton.setBackground(fondo);
        noAceptarButton.setForeground(texto);
        terminosTextArea.setBackground(fondo);
        terminosTextArea.setForeground(texto);
        buttonPanel.setBackground(fondo);  // Cambiar el fondo del panel que contiene los botones
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        new TerminosYCondiciones("Usuario").setVisible(true);
    }
}
