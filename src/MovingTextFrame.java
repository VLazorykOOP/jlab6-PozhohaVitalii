import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingTextFrame extends JFrame implements ActionListener {
    private JLabel label;
    private Timer timer;
    private int x = 10; // Початкове положення рядка по осі X
    private int deltaX = 2; // Крок зміщення рядка по осі X

    private int deltaY = 1; // Крок зміщення рядка по осі X

    private JComboBox<String> colorComboBox;
    private JPanel placeholderPanel;
    public MovingTextFrame() {
        placeholderPanel = new JPanel();
        placeholderPanel.setBackground(Color.CYAN);
        placeholderPanel.setPreferredSize(new Dimension(250, 100));
        add(placeholderPanel);
        setTitle("DVD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        placeholderPanel.setLayout(new FlowLayout());
//        placeholderPanel.setLayout(new BorderLayout());
        setLocation(600,400);


        label = new JLabel("DVD");
        label.setFont(new Font("Arial", Font.BOLD, 60));
        placeholderPanel.add(label);


        // Створення спадного списку з кольорами
        String[] colors = {"Червоний", "Зелений", "Синій"};
        colorComboBox= new JComboBox<String>(colors);
        colorComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        colorComboBox.setBackground(Color.ORANGE);
        colorComboBox.setAutoscrolls(true);
        colorComboBox.setForeground(Color.BLACK);
        colorComboBox.setName("TOOlcha");
        colorComboBox.setSelectedIndex(2);
        colorComboBox = colorComboBox;

        placeholderPanel.add(colorComboBox);

//        placeholderPanel.add(colorComboBox, BorderLayout.NORTH);
////        colorComboBox.setSize(colorComboBox.getPreferredSize());
////       colorComboBox.setAlignmentY(getAlignmentY()+30);
////       colorComboBox.setAlignmentX(getAlignmentX()+30);



        // Створення таймера для руху рядка
        timer = new Timer(10, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        // Зміщення рядка
        x += deltaX;

        // Перевірка відбивання рядка від границь фрейму
        if (x <= 0 || x + label.getWidth()+15 >= getWidth()) {
            deltaX *= -1; // Зміна напрямку руху рядка
            changeLabelColor(); // Зміна кольору рядка
        }
        if (label.getY() <= 0 || label.getY() + label.getHeight() >= getHeight()-40) {
            deltaY *= -1; // Зміна напрямку руху рядка
            changeLabelColor(); // Зміна кольору рядка
        }


        // Зміщення рядка на нову позицію
        label.setLocation(x, label.getY()+deltaY);
    }

    private void changeLabelColor() {
        String selectedColor = (String) colorComboBox.getSelectedItem();

        // Зміна кольору рядка в залежності від обраного значення зі спадного списку
        if (selectedColor.equals("Червоний")) {
            label.setForeground(Color.RED);
        } else if (selectedColor.equals("Зелений")) {
            label.setForeground(Color.GREEN);
        } else if (selectedColor.equals("Синій")) {
            label.setForeground(Color.BLUE);
        }
    }

    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MovingTextFrame frame = new MovingTextFrame();
                frame.setVisible(true);
            }
        });
    }
}
