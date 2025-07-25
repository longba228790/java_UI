package com.example.predictor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PredictorGUI extends JFrame {
    private JTextField x1Field, x2Field, x3Field, x4Field, x5Field;
    private JTextField yInputField;
    private JLabel resultLabel;

    public PredictorGUI() {
        setTitle("Graphene/MoS2 Composite Conductivity Prediction System");
        setSize(750, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("<html>Graphene/MoS<sub>2</sub> Composite Conductivity Prediction System</html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 180, 180)));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setPreferredSize(new Dimension(getWidth(), 60));
        add(titleLabel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tabbedPane.setBackground(Color.WHITE);

        JPanel forwardPanel = createForwardPredictionPanel();
        JPanel reversePanel = createReversePredictionPanel();

        tabbedPane.addTab("Forward Prediction", forwardPanel);
        tabbedPane.addTab("Reverse Condition Finder", reversePanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createForwardPredictionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(25, 30, 30, 30));
        panel.setBackground(Color.WHITE);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(new TitledBorder(new LineBorder(new Color(200, 200, 200), 1), "Experimental Parameters Input", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 16)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);

        x1Field = new JTextField(12); x1Field.setFont(inputFont);
        x2Field = new JTextField(12); x2Field.setFont(inputFont);
        x3Field = new JTextField(12); x3Field.setFont(inputFont);
        x4Field = new JTextField(12); x4Field.setFont(inputFont);
        x5Field = new JTextField(12); x5Field.setFont(inputFont);

        int row = 0;
        addRow(inputPanel, gbc, row++, "Reaction Time x1 (h): [1–10]", x1Field, labelFont);
        addRow(inputPanel, gbc, row++, "Air Flow Rate x2 (L/h): [12–36]", x2Field, labelFont);
        addRow(inputPanel, gbc, row++, "Current Intensity x3 (mA): [12–20]", x3Field, labelFont);
        addRow(inputPanel, gbc, row++, "Feed Ratio x4: [0.5–3.0]", x4Field, labelFont);
        addRow(inputPanel, gbc, row++, "NaCl Concentration x5 (% w/v): [1.0–10.0]", x5Field, labelFont);

        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBorder(new TitledBorder(new LineBorder(new Color(200, 200, 200), 1), "Prediction Result", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 16)));

        resultLabel = new JLabel("Predicted Conductivity: ");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultPanel.add(resultLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        JButton calculateButton = new JButton("Start Forward Prediction");
        calculateButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        calculateButton.setBackground(new Color(0, 122, 204));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setPreferredSize(new Dimension(230, 40));
        buttonPanel.add(calculateButton);

        calculateButton.addActionListener((ActionEvent e) -> {
            try {
                double x1 = Double.parseDouble(x1Field.getText());
                double x2 = Double.parseDouble(x2Field.getText());
                double x3 = Double.parseDouble(x3Field.getText());
                double x4 = Double.parseDouble(x4Field.getText());
                double x5 = Double.parseDouble(x5Field.getText());

                double result = Calculator.predictConductivity(x1, x2, x3, x4, x5);
                resultLabel.setText(String.format("Predicted Conductivity: %.4f μS/cm", result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(resultPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createReversePredictionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(25, 30, 30, 30));
        panel.setBackground(Color.WHITE);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(new TitledBorder(new LineBorder(new Color(200, 200, 200), 1), "Target Conductivity Input", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 16)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);

        yInputField = new JTextField(12);
        yInputField.setFont(inputFont);

        addRow(inputPanel, gbc, 0, "Target Conductivity y (μS/cm):", yInputField, labelFont);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        JButton reverseButton = new JButton("Find Experimental Conditions");
        reverseButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        reverseButton.setBackground(new Color(0, 122, 204));
        reverseButton.setForeground(Color.WHITE);
        reverseButton.setFocusPainted(false);
        reverseButton.setPreferredSize(new Dimension(250, 40));
        buttonPanel.add(reverseButton);

        reverseButton.addActionListener((ActionEvent e) -> {
            try {
                double targetY = Double.parseDouble(yInputField.getText());
                double tolerance = 0.01;

                List<String> results = ReversePredictor.findMatchingConditions(targetY, tolerance);

                if (results.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No matching experimental combinations found.", "Result", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    showResultDialog(results);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid conductivity value.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JLabel noteLabel = new JLabel("Note: The system uses an empirical regression model with ±0.01 μS/cm tolerance.");
        noteLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        noteLabel.setForeground(Color.DARK_GRAY);
        JPanel notePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        notePanel.setBackground(Color.WHITE);
        notePanel.add(noteLabel);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(notePanel, BorderLayout.SOUTH);

        return panel;
    }

    private void showResultDialog(List<String> results) {
        JDialog resultDialog = new JDialog(this, "Experimental Combinations (±0.01 μS/cm)", true);
        resultDialog.setSize(750, 450);
        resultDialog.setLocationRelativeTo(this);
        resultDialog.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Matching Experimental Conditions", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerLabel.setBorder(new EmptyBorder(15, 10, 15, 10));
        resultDialog.add(headerLabel, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 15));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setBackground(Color.WHITE);
        area.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        for (String r : results) {
            area.append(r + "\n\n");
        }
        JScrollPane scrollPane = new JScrollPane(area);
        resultDialog.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        closeButton.setPreferredSize(new Dimension(100, 35));
        closeButton.addActionListener(ev -> resultDialog.dispose());
        buttonPanel.add(closeButton);

        resultDialog.add(buttonPanel, BorderLayout.SOUTH);
        resultDialog.setVisible(true);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JTextField field, Font font) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
        gbc.weightx = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PredictorGUI gui = new PredictorGUI();
            gui.setVisible(true);
        });
    }
}
