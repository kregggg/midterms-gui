import java.awt.*;
import javax.swing.*;

public class PolynomialArithmetic {
    static JFrame mainFrame = new JFrame("Polynomial Arithmetic");

    public static void main(String[] args) {
        // Initialize main frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setBackground(new Color(222, 216, 211));
        mainFrame.setLayout(null);

        // Set application favicon
        ImageIcon mainIcon = new ImageIcon("icon.png"); // Insert image path inside parenthesis
        mainFrame.setIconImage(mainIcon.getImage());

        // Add main menu panels
        createMenuPanel();

        // Add components to main frame (must be done last)
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public static void createWelcomePanel() {
        JPanel welcomeMessagePanel = new JPanel();
        welcomeMessagePanel.setBackground(new Color(222, 216, 211));
        welcomeMessagePanel.setBounds(120, 0, 350, 150); // 👈 important
        welcomeMessagePanel.setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel welcomeMessage = new JLabel("Welcome to Polynomial Arithmetic");
        welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeMessage.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel label1 = new JLabel("SELECT OPERATION TO BE DONE");
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setFont(new Font("Serif", Font.BOLD, 20));

        // Add vertical spacing between labels
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(welcomeMessage);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(label1);

        welcomeMessagePanel.add(textPanel, BorderLayout.SOUTH);

        mainFrame.add(welcomeMessagePanel);
    }

    public static void createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(150, 150, 300, 300);
        panel.setLayout(new GridLayout(5, 1, 0, 10));
        panel.setBackground(new Color(222, 216, 211));

        JButton button1 = new JButton("Evaulate Polynomial");
        JButton button2 = new JButton("Addition");
        JButton button3 = new JButton("Subtraction");
        JButton button4 = new JButton("Multiplication");
        JButton button5 = new JButton("Division");

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);
        button5.setFocusable(false);

        button1.addActionListener(e -> evaluatePolynomial());
        button2.addActionListener(e -> operationPanel("Addition"));
        button3.addActionListener(e -> operationPanel("Subtraction"));
        button4.addActionListener(e -> operationPanel("Multiplication"));
        button5.addActionListener(e -> operationPanel("Division"));

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);

        createWelcomePanel();
        mainFrame.add(panel);
    }

    public static void evaluatePolynomial() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(222, 216, 211));
        panel.setBounds(0, 0, 600, 600);
        panel.setLayout(null); // manual layout

        // === Subpanel 1 (Polynomial Input) ===
        JPanel polyPanel = new JPanel();
        polyPanel.setBounds(100, 50, 400, 300);
        polyPanel.setBackground(new Color(240, 240, 240));
        polyPanel.setLayout(new BorderLayout(5, 5));

        JLabel polyLabel = new JLabel("Polynomial to be Evaluated", SwingConstants.CENTER);
        polyLabel.setFont(new Font("Serif", Font.BOLD, 18));

        JTextArea polyArea = new JTextArea();
        polyArea.setLineWrap(true);
        polyArea.setWrapStyleWord(true);
        polyPanel.add(polyLabel, BorderLayout.NORTH);
        polyPanel.add(new JScrollPane(polyArea), BorderLayout.CENTER);

        // === Subpanel 2 (Literal Value Input) ===
        JPanel literalPanel = new JPanel();
        literalPanel.setBounds(100, 370, 400, 80);
        literalPanel.setBackground(new Color(240, 240, 240));
        literalPanel.setLayout(new BorderLayout(5, 5));

        JLabel literalLabel = new JLabel("Literal Value", SwingConstants.CENTER);
        literalLabel.setFont(new Font("Serif", Font.BOLD, 16));

        JTextField literalField = new JTextField();
        literalField.setHorizontalAlignment(JTextField.CENTER);
        literalField.setFont(new Font("Serif", Font.PLAIN, 16));

        literalPanel.add(literalLabel, BorderLayout.NORTH);
        literalPanel.add(literalField, BorderLayout.CENTER);

        // === Buttons ===
        JButton evaluateButton = new JButton("Evaluate");
        evaluateButton.setBounds(200, 480, 100, 40);
        evaluateButton.addActionListener(e -> {
            String polyInput = polyArea.getText();
            String literalInput = literalField.getText();

            if (!isValidPolynomial(polyInput)) {
                JOptionPane.showMessageDialog(null, "Invalid Polynomial Input!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double literalValue = Double.parseDouble(literalInput);
                Polynomial poly = extractPolynomial(polyInput);

                // Call your evaluation logic here
                double result = poly.evaluate(literalValue); // assuming you have evaluate() in Polynomial
                JOptionPane.showMessageDialog(null, ("Result: " + result), "Evaluation Result", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid literal value!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred during evaluation.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(320, 480, 100, 40);
        backButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll();
            createMenuPanel();
            mainFrame.revalidate();
            mainFrame.repaint();
        });

        // === Add all components ===
        panel.add(polyPanel);
        panel.add(literalPanel);
        panel.add(evaluateButton);
        panel.add(backButton);

        // === Display panel ===
        mainFrame.getContentPane().removeAll();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static void operationPanel(String operation) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(222, 216, 211));
        panel.setBounds(0, 0, 600, 600);
        panel.setLayout(null); // manual positioning

        // === Subpanel 1 (First Polynomial) ===
        JPanel firstPanel = new JPanel();
        firstPanel.setBounds(100, 50, 400, 200);
        firstPanel.setBackground(new Color(240, 240, 240));
        firstPanel.setLayout(new BorderLayout(5, 5));

        // Create a small panel for the label + example
        JPanel firstLabelPanel = new JPanel();
        firstLabelPanel.setLayout(new BoxLayout(firstLabelPanel, BoxLayout.Y_AXIS));
        firstLabelPanel.setOpaque(false);

        JLabel label1 = new JLabel("First Polynomial", SwingConstants.CENTER);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setFont(new Font("Serif", Font.BOLD, 16));

        JLabel example1 = new JLabel("Example: 4x^2 +9x +5", SwingConstants.CENTER);
        example1.setAlignmentX(Component.CENTER_ALIGNMENT);
        example1.setFont(new Font("Serif", Font.ITALIC, 14));
        example1.setForeground(Color.DARK_GRAY);

        firstLabelPanel.add(label1);
        firstLabelPanel.add(example1);

        JTextArea textArea1 = new JTextArea();
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);

        firstPanel.add(firstLabelPanel, BorderLayout.NORTH);
        firstPanel.add(new JScrollPane(textArea1), BorderLayout.CENTER);

        // === Subpanel 2 (Second Polynomial) ===
        JPanel secondPanel = new JPanel();
        secondPanel.setBounds(100, 280, 400, 200);
        secondPanel.setBackground(new Color(240, 240, 240));
        secondPanel.setLayout(new BorderLayout(5, 5));

        // Label + optional example for second panel
        JPanel secondLabelPanel = new JPanel();
        secondLabelPanel.setLayout(new BoxLayout(secondLabelPanel, BoxLayout.Y_AXIS));
        secondLabelPanel.setOpaque(false);

        JLabel label2 = new JLabel("Second Polynomial", SwingConstants.CENTER);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setFont(new Font("Serif", Font.BOLD, 16));

        JLabel example2 = new JLabel("Example: 2x^3 +7x +1", SwingConstants.CENTER);
        example2.setAlignmentX(Component.CENTER_ALIGNMENT);
        example2.setFont(new Font("Serif", Font.ITALIC, 14));
        example2.setForeground(Color.DARK_GRAY);

        secondLabelPanel.add(label2);
        secondLabelPanel.add(example2);

        JTextArea textArea2 = new JTextArea();
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);

        secondPanel.add(secondLabelPanel, BorderLayout.NORTH);
        secondPanel.add(new JScrollPane(textArea2), BorderLayout.CENTER);

        // === Determine operation button label ===
        String buttonLabel = "";
        switch (operation){
            case "Addition" -> buttonLabel = "Add";
            case "Subtraction" -> buttonLabel = "Subtract";
            case "Multiplication" -> buttonLabel = "Multiply";
            case "Division" -> buttonLabel = "Divide";
        }

        // === Button to get text ===
        JButton getTextButton = new JButton(buttonLabel);
        getTextButton.setBounds(200, 500, 100, 40);
        getTextButton.addActionListener(e -> {
            String firstPoly = textArea1.getText();
            String secondPoly = textArea2.getText();
            if (!isValidPolynomial(firstPoly) || !isValidPolynomial(secondPoly)){
                JOptionPane.showMessageDialog(null, "Invalid Polynomial Input! ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Example: splitting terms for further parsing
                try {
                    Polynomial pol1 = extractPolynomial(firstPoly);
                    Polynomial pol2 = extractPolynomial(secondPoly);

                    if (pol1.getTerms().get(0).getLiteral() == pol2.getTerms().get(0).getLiteral()){
                        switch (operation){
                            case "Addition" -> showResults(operation, pol1, pol2);
                            case "Subtraction" -> showResults(operation, pol1, pol2);
                            case "Multiplication" -> showResults(operation, pol1, pol2);
                            case "Division" -> showResults(operation, pol1, pol2);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Polynomial Input! \nPolynomials should have the same literal/variable.", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // === Button to go back to the menu ===
        JButton backButton = new JButton("Back");
        backButton.setBounds(320, 500, 100, 40);
        backButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll();
            createMenuPanel(); // 👈 back to main menu
            mainFrame.revalidate();
            mainFrame.repaint();
        });

        // === Add components to main panel ===
        panel.add(firstPanel);
        panel.add(secondPanel);
        panel.add(getTextButton);
        panel.add(backButton);

        // === Replace old content ===
        mainFrame.getContentPane().removeAll();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static Polynomial extractPolynomial(String expression) throws Exception {
        if (expression == null || expression.isBlank())
            throw new Exception("Empty polynomial input.");

        // Remove spaces for easier parsing
        expression = expression.replaceAll("\\s+", "");

        // Split terms by + or -, keeping the sign with each term
        String[] terms = expression.split("(?=[+-])");

        Polynomial result = new Polynomial();

        for (String termExp : terms) {
            if (termExp.isBlank()) continue;

            Term term = new Term();

            // Default coefficient and exponent
            int coefficient = 0;
            int exponent = 0;
            char literal = '\0';

            // === Extract coefficient and variable ===
            int varIndex = -1;
            for (int i = 0; i < termExp.length(); i++) {
                if (Character.isAlphabetic(termExp.charAt(i))) {
                    varIndex = i;
                    break;
                }
            }

            if (varIndex == -1) {
                // Constant term (no variable)
                coefficient = Integer.parseInt(termExp);
                exponent = 0;
            } else {
                String coeffPart = termExp.substring(0, varIndex);
                literal = termExp.charAt(varIndex);

                if (coeffPart.equals("") || coeffPart.equals("+")) coeffPart = "1";
                else if (coeffPart.equals("-")) coeffPart = "-1";

                coefficient = Integer.parseInt(coeffPart);

                // Check for exponent
                int caretIndex = termExp.indexOf('^', varIndex);
                if (caretIndex != -1) {
                    String expPart = termExp.substring(caretIndex + 1);
                    try {
                        exponent = Integer.parseInt(expPart);
                    } catch (NumberFormatException e) {
                        throw new Exception("Invalid exponent in term: " + termExp);
                    }
                } else {
                    exponent = 1; // default
                }
            }

            term.setCoefficient(coefficient);
            term.setLiteral(literal);
            term.setDegree(exponent);

            result.addTerm(term);
        }

        return result;
    }

    public static boolean isValidPolynomial(String input) {
        if (input == null || input.isBlank()) return false;

        // Remove extra spaces (e.g., between + and terms)
        input = input.trim().replaceAll("\\s+", "");

        // Pattern for one valid term:
        // optional sign, optional digits, optional variable, optional exponent
        String termPattern = "[-+]?\\d*([a-zA-Z](\\^\\d+)?)?";

        // Full polynomial = one or more terms separated by + or -
        String fullPattern = termPattern + "([+-]" + termPattern + ")*";

        // Validate entire input
        return input.matches(fullPattern);
    }

    public static void showResults(String operation, Polynomial p1, Polynomial p2) throws Exception {
        switch (operation){
            case "Addition":
                Polynomial sum = p1.add(p2);
                JOptionPane.showMessageDialog(null, ("Sum of polynomials:\n" + sum.toString()));
                break;
            case "Subtraction":
                Polynomial diff = p1.subtract(p2);
                JOptionPane.showMessageDialog(null, ("Difference of polynomials:\n" + diff.toString()));
                break;
            case "Multiplication":
                Polynomial product = p1.multiply(p2);
                JOptionPane.showMessageDialog(null, ("Product of polynomials:\n" + product.toString()));
                break;
            case "Division":
                Quotient quot = p1.divide(p2);
                JOptionPane.showMessageDialog(null, ("Quotient of polynomials:\n" + quot.toString()));
                break;
        }
    }

}
