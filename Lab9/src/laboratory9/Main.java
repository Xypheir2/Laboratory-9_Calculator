package laboratory9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    private JTextField display;
    private int total = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public Main() {
        setTitle("LABORATORY 9");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 500);
        setLocationRelativeTo(null);

        display = new JTextField("0");
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 15, 15));
        add(buttonPanel, BorderLayout.CENTER);

        String[] buttons = {
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "x",
            "0", "C", "/", "="
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            buttonPanel.add(button);
        }
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if (command.equals("C")) {
            total = 0;
            operator = "";
            display.setText("0");
            startNewNumber = true;
        } else if (command.equals("+") || command.equals("-") || command.equals("x") || command.equals("/")) {
            if (!operator.equals("")) {
                calculate();
            } else {
                total = convertStringToInt(display.getText());
            }
            operator = command;
            startNewNumber = true;
        } else if (command.equals("=")) {
            calculate();
            operator = "";
            startNewNumber = true;
        } else {
            if (startNewNumber) {
                display.setText(command);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + command);
            }
        }
    }

    private void calculate() {
        int currentNumber = convertStringToInt(display.getText());

        if (operator.equals("+")) {
            total = total + currentNumber;
        } else if (operator.equals("-")) {
            total = total - currentNumber;
        } else if (operator.equals("x")) {
            total = total * currentNumber;
        } else if (operator.equals("/")) {
            if (currentNumber != 0) {
                total = total / currentNumber;
            } else {
                display.setText("Error");
                total = 0;
                operator = "";
                startNewNumber = true;
                return;
            }
        }
        display.setText("" + total);
    }

    private int convertStringToInt(String text) {
        int number = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= '0' && c <= '9') {
                number = number * 10 + (c - '0');
            } else {
                return 0;
            }
        }
        return number;
    }


    public static void main(String[] args) {
        Main calculator = new Main();
        calculator.setVisible(true);
    }
}
