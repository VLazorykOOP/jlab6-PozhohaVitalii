import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import  java.io.*;

public class Matrix extends JFrame implements ActionListener {
    private  int N;
    private  int matrix [][];

    private JTextField A;
    private JTextField A1;
    private JTextField[][] matrixFields;

    private JButton B1;
    private  JButton B2;
    private  JButton B3;
    public Matrix(){
        setTitle("MatriX");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(600,300);
        setLocation(500,400);
        A = new JTextField("Size of");
        A.setFont(new Font("Arial", Font.ITALIC, 16));

        A.setBounds(40,40,70,40);
        B1 = new JButton("Size Init");
        B1.setFont(new Font("Arial", Font.BOLD, 20));
        B1.setBackground(Color.yellow);

        B1.setBounds(20,100,110,50);
        B1.addActionListener(e -> matrixInit());

        B2 = new JButton("Matrix Calculate");
        B2.setFont(new Font("Arial", Font.BOLD, 20));
        B2.setBackground(Color.yellow);

        B2.setBounds(20,170,110,50);
        B2.addActionListener(e -> matrixCalc());

        B3 = new JButton("File Input");
        B3.setFont(new Font("Arial", Font.BOLD, 20));
        B3.setBackground(Color.yellow);

        B3.setBounds(435,170,110,50);
        B3.addActionListener(e -> matrixFile());

        A1 = new JTextField("Result");
        A1.setFont(new Font("Arial", Font.ITALIC, 16));

        A1.setBounds(440,40,100,40);


        add(A, BorderLayout.NORTH);
        add(A1);
        add(B1,BorderLayout.SOUTH);
        add(B2);
        add(B3);
        //pack();
        setVisible(true);
    }
    void matrixFile(){
        String filename = "C:\\Users\\verto\\IdeaProjects\\jlab6-PozhohaVitalii\\src\\input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int rows = N;
            int columns = N;






            int[][] matrix = new int[rows][columns];

            for (int i = 0; i < rows; i++) {
                if ((line = reader.readLine()) != null) {
                    String[] elements = line.trim().split("\\s+");
                    for (int j = 0; j < columns; j++) {
                        matrixFields[i][j].setText(elements[j]);
                        matrix[i][j] = Integer.parseInt(elements[j]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    void matrixCalc(){
        if (N!=0){
            int rows = N;
            int columns = N;
            matrix = new int[N][N];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    boolean F1 = true;
                    int F = 0;
                    while (F1){

                    try {
                        F = Integer.parseInt(matrixFields[i][j].getText());
                        System.out.println("Melem"+ i + j + F);
                        F1 = false;
                    } catch (NumberFormatException er) {
                        F1 = true;
                        System.out.println("Conversion failed. The string is not a valid integer.");
                    }
                    if (F1){
                        String input = JOptionPane.showInputDialog("Enter an item (index) :" + i + " " + j);
                        matrixFields[i][j].setText(input);
                    }
                    }
                    matrix[i][j] = F;

                }
            }
            String Astr = "Result is " + findMin();
            A1.setText(Astr);

        }


    }
    void matrixInit (){

            int s = 0;
            try {
                s = Integer.parseInt(A.getText());
                System.out.println("Conversion successful. The integer value is: " + s);
            } catch (NumberFormatException er) {
                System.out.println("Conversion failed. The string is not a valid integer.");
            }
            N = s;
            if (A.getText().isEmpty()|| s==0 ){
                String input = JOptionPane.showInputDialog("Enter an item:");
                A.setText(input);
            }
            else {
                int rows = s;
                int columns = s;
                JPanel matrixPanel = new JPanel();

                matrixPanel.setLayout(new GridLayout(rows, columns));
                matrixPanel.setBounds(230,40,s*40,s*40);

                matrixFields = new JTextField[rows][columns];


                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        JTextField field = new JTextField();
                        matrixFields[i][j] = field;
                        matrixFields[i][j].setFont(new Font("Arial", Font.BOLD, 15));
                        matrixPanel.add(field);
                    }
                }


                getContentPane().remove(matrixPanel);
                add(matrixPanel, BorderLayout.CENTER);
                revalidate();
                repaint();

            }




    }
    public void actionPerformed(ActionEvent e) {


    }
    private int findRow(){
        int i=0,j=0;
        int[] sum = new int [N];
        for(i=0;i<N;i++){
            for(j=0;j<N;j++) {
                sum[i] += Math.abs( matrix[j][i]);
            }
        }
        int max = 0;
        int indMax = 0;
        for(i=0;i<N;i++){
            if (max<sum[i]){
                max = sum[i];
                indMax = i;
            }
        }
        System.out.println();
        System.out.println(max + "\t " + (indMax+1) );
        return indMax;
    }
    public int findMin(){
        int i=0, j=0, index = findRow();
        int min = matrix[0][index], indMin = 0;
        for(i=0;i<N;i++){
            if (min > matrix[i][index]){
                min = matrix[i][index];
                indMin = i;
            }
        }
        System.out.println();
        System.out.println(min + "\t " + (indMin+1) );
        return min;
    }

    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Matrix frame = new Matrix();
                frame.setVisible(true);
            }
        });
    }
}
