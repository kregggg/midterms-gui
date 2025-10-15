/*
    Name:
        Solomon, Craig David T.
    Date:
        October 16, 2025 (Midterms Individual Project)

    Problem:
        Complete and organize the Polynomial Arithmetic code given

    Algorithm (USING GUI)
        1. Create main frame where welcome message and main menu options/buttons will be placed
        2. Set buttons so that each button will open its corresponding panel and method
            2.1. Evaluate Polynomial -> evaluatePolynomial()
            2.2. Addition of Polynomials -> operationsPanel("Addition")
            2.3. Subtraction of Polynomials -> operationsPanel("Subtraction")
            2.4. Multiplication of Polynomials -> -> operationsPanel("Multiplication")
            2.5. Division of Polynomials -> operationsPanel("Addition")
        3. Allow user to input 2 Polynomial expressions
            3.1. For evaluatePolynomial, allow only 1 polynomial input and ask user to value of literal
        4. After successfully following format of polynomial, click of <operations> button
        5. Pop up message will appear that will indicate result of operations
        6. Return to main menu panel until program is closed
*/

import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

/**
 * A program that allows user to perform computations of polynomials.
 * <p>
 *     Allows polynomial evaluation and basic arithmetic operations (such as addition,
 *     subtraction, multiplication, and division)
 * </p>
 */
public class PolynomialArithmetic {
    static JFrame mainFrame = new JFrame("Polynomial Arithmetic");
    Scanner keyboard = new Scanner(System.in);

    /**
     * Main method that initializes and runs the program.
     * <p>
     *     Starts up the Graphical User Interface (GUI) for the program.
     *     Initializes the JFrame where option panels will be added to.
     * </p>
     * @param args
     */
    public static void main(String[] args) {
        // Initialize main frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setBackground(new Color(222, 216, 211));
        mainFrame.setLayout(null);

        // Set application favicon
        ImageIcon mainIcon = new ImageIcon("icon.png");
        mainFrame.setIconImage(mainIcon.getImage());

        createMenuPanel();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /**
     * Creates welcome message along with sentence prompt for user to select action to be done.
     * <p>
     *     Creates a header panel for the JFrame.
     *     Contains welcome message for the user and prompt to select an action to be done in the polynomial.
     * </p>
     */
    public static void createWelcomePanel() {
        JPanel welcomeMessagePanel = new JPanel();
        welcomeMessagePanel.setBackground(new Color(222, 216, 211));
        welcomeMessagePanel.setBounds(120, 0, 350, 150);
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

        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(welcomeMessage);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(label1);

        welcomeMessagePanel.add(textPanel, BorderLayout.SOUTH);
        mainFrame.add(welcomeMessagePanel);
    }

    /**
     * Contains main menu options.
     * <p>
     *    A panel that consists of the 5 buttons with corresponding actions for the program.
     *    Actions include the following:
     *    1. Evaluate Program
     *    2. Addition of Polynomials
     *    3. Subtraction of Polynomials
     *    4. Multiplication of Polynomials
     *    5. Division of Polynomials
     * </p>
     */
    public static void createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(150, 150, 300, 300);
        panel.setLayout(new GridLayout(5, 1, 0, 10));
        panel.setBackground(new Color(222, 216, 211));

        JButton button1 = new JButton("Evaluate Polynomial");
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

    /**
     * Initializes new panel to allow user to input necessary information to evaluate/solve a function.
     * <p>
     *     Creates a panel that will take over the program GUI.
     *     Allows user to input polynomial function and the value of the literal in the function.
     *     Substitutes the value into the literal and solves using the Polynomial.evaluate() method.
     * </p>
     */
    public static void evaluatePolynomial() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(222, 216, 211));
        panel.setBounds(0, 0, 600, 600);
        panel.setLayout(null); // manual layout

        // Panel for polynomial to be evaluated
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

        // Panel for value of literal/variable
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

        // Buttons
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

        panel.add(polyPanel);
        panel.add(literalPanel);
        panel.add(evaluateButton);
        panel.add(backButton);

        mainFrame.getContentPane().removeAll();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /**
     * Creates operation panel to allow user to perform actions depending on selected operation.
     * <p>
     *     Creates a panel that will take over the program GUI.
     *     Allow user to input 2 polynomial functions.
     *     Offer a button to solve the user input polynomials.
     *     Solve Polynomial operation based on user input.
     *     a. Addition
     *     b. Subtraction
     *     c. Multiplication
     *     d. Division
     * </p>
     * @param operation controls the operation to be done, based on button output from createMenuPanel()
     */
    public static void operationPanel(String operation) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(222, 216, 211));
        panel.setBounds(0, 0, 600, 600);
        panel.setLayout(null);

        // First polynomial in operation (upper tab)
        JPanel firstPanel = new JPanel();
        firstPanel.setBounds(100, 50, 400, 200);
        firstPanel.setBackground(new Color(240, 240, 240));
        firstPanel.setLayout(new BorderLayout(5, 5));

        // Label and example
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

        // Second polynomial in operation (lower tab)
        JPanel secondPanel = new JPanel();
        secondPanel.setBounds(100, 280, 400, 200);
        secondPanel.setBackground(new Color(240, 240, 240));
        secondPanel.setLayout(new BorderLayout(5, 5));

        // Label and example
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

        // Operation button set up
        String buttonLabel = "";
        switch (operation){
            case "Addition" -> buttonLabel = "Add";
            case "Subtraction" -> buttonLabel = "Subtract";
            case "Multiplication" -> buttonLabel = "Multiply";
            case "Division" -> buttonLabel = "Divide";
        }

        // Extract polynomial and start computation
        JButton getTextButton = new JButton(buttonLabel);
        getTextButton.setBounds(200, 500, 100, 40);
        getTextButton.addActionListener(e -> {
            String firstPoly = textArea1.getText();
            String secondPoly = textArea2.getText();
            if (!isValidPolynomial(firstPoly) || !isValidPolynomial(secondPoly)){
                // If polynomial does not follow given format
                JOptionPane.showMessageDialog(null, "Invalid Polynomial Input! ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
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

        JButton backButton = new JButton("Back");
        backButton.setBounds(320, 500, 100, 40);
        backButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll();
            createMenuPanel();
            mainFrame.repaint();
        });

        panel.add(firstPanel);
        panel.add(secondPanel);
        panel.add(getTextButton);
        panel.add(backButton);

        mainFrame.getContentPane().removeAll();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    /**
     * Extracts polynomial from user input in text area/boxes.
     * <p>
     *     Creates a new array that splits user input by separating values by a space(" ").
     *     Creates a new Term based on each content of the split array.
     *     Adds each term to a new polynomial to be returned.
     * </p>
     * @param expression is the user input in the text area/box.
     * @return an extracted polynomial from the String expression parameter.
     * @throws Exception for invalid user inputs.
     */
    public static Polynomial extractPolynomial(String expression) throws Exception {
        if (expression == null || expression.isBlank()) {
            throw new Exception("Empty polynomial input.");
        }

        expression = expression.replaceAll("\\s+", "");

        String[] terms = expression.split("(?=[+-])");

        Polynomial result = new Polynomial();

        for (String termExp : terms) {
            if (termExp.isBlank()){
                continue;
            }
            Term term = new Term();

            int coefficient = 0;
            int exponent = 0;
            char literal = '\0';

            int varIndex = -1;
            for (int i = 0; i < termExp.length(); i++) {
                if (Character.isAlphabetic(termExp.charAt(i))) {
                    varIndex = i;
                    break;
                }
            }

            if (varIndex == -1) {
                coefficient = Integer.parseInt(termExp);
                exponent = 0;
            } else {
                String coeffPart = termExp.substring(0, varIndex);
                literal = termExp.charAt(varIndex);

                if (coeffPart.equals("") || coeffPart.equals("+")) coeffPart = "1";
                else if (coeffPart.equals("-")) coeffPart = "-1";

                coefficient = Integer.parseInt(coeffPart);

                int caretIndex = termExp.indexOf('^', varIndex);
                if (caretIndex != -1) {
                    String expPart = termExp.substring(caretIndex + 1);
                    try {
                        exponent = Integer.parseInt(expPart);
                    } catch (NumberFormatException e) {
                        throw new Exception("Invalid exponent in term: " + termExp);
                    }
                } else {
                    exponent = 1;
                }
            }

            term.setCoefficient(coefficient);
            term.setLiteral(literal);
            term.setDegree(exponent);

            result.addTerm(term);
        }

        return result;
    }

    /**
     * Checks user input if it is considered a "valid" polynomial by the system.
     * <p>
     *     A considered proper polynomial is when a polynomial follows the format given:
     *     4x^3 +3x^2 -9x +2
     *     Spaces should be added after each term.
     *     While operations & positive/negative indicators should be attached to coefficient (no spaces).
     * </p>
     * @param input is the user input polynomial to be validated.
     * @return a boolean value depending on user polynomial, if it is "valid" or not.
     */
    public static boolean isValidPolynomial(String input) {
        if (input == null || input.isBlank()) return false;

        input = input.trim().replaceAll("\\s+", "");

        String termPattern = "[-+]?\\d*([a-zA-Z](\\^\\d+)?)?";

        String fullPattern = termPattern + "([+-]" + termPattern + ")*";

        return input.matches(fullPattern);
    }

    /**
     * Creates information message window to show results/output of computations.
     * <p>
     *     Creates a JOptionPane to inform user about results of the computation.
     * </p>
     * @param operation is a string value that indicates the operation done, used for output labels.
     * @param p1 is the first polynomial in the equation.
     * @param p2 is the seconds polynomial in the equation.
     * @throws Exception for possible no matches in the switch case or errors in the JOptionPane message.
     */
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

    /**
     * Handles Main menu options for text based output.
     * <p>
     *     Main connection of all methods. Handles choice input, method call for printing menu, operation methods
     *     choice validation, and code termination.
     * </p>
     * @throws Exception for possible invalid inputs.
     */
    public void run() throws Exception{
        byte choice = 0;
        while ( choice != 6 ) {
            showMenu();
            choice = readChoice((byte) 1, (byte) 6);
            switch (choice){
                case 1:
                    evaluatePolynomialText();
                    break;
                case 2:
                    addPolynomials();
                    break;
                case 3:
                    subtractPolynomials();
                    break;
                case 4:
                    multiplyPolynomials();
                    break;
                case 5:
                    dividePolynomials();
                    break;
                case 6:
                    System.out.println("Thank you for using this program.");
            } // end of swicth
        } // end of while
    } // end of run

    /**
     * Allows user to input a choice in the menu for the text based execution.
     * <p>
     *     Allow user to input a number choice within the given range of 'low' and 'high' .
     * </p>
     * @param low is the number that corresponds to the lowest number that corresponds to a choice in the menu.
     * @param high is the number that corresponds to the highest number that corresponds to a choice in the menu.
     * @return the user input number (the 'choice').
     * @throws Exception for invalid inputs.
     */
    private byte readChoice(byte low, byte high) throws Exception{
        byte choice=0;
        System.out.print("Enter your choice<"+ low + "... " + high + ">: ");
        choice = (byte) readInteger(low, high);
        return choice;
    }

    /**
     * Prints main menu that shows all options offered by the program. Used in the text based execution.
     * <p>
     *     Contains the following choices
     *     1. Evaluate a polynomial
     *     2. Add two polynomials
     *     3. Subtract a polynomial from another polynomial
     *     4. Multiply two polynomials
     *     5. Divide a polynomial by another polynomial
     *     6. Quit
     * </p>
     */
    public void showMenu() {
        System.out.println("-----------------------MENU--------------------------");
        System.out.println("1. Evaluate a polynomial");
        System.out.println("2. Add two polynomials");
        System.out.println("3. Subtract a polynomial from another polynomial");
        System.out.println("4. Multiply two polynomials");
        System.out.println("5. Divide a polynomial by another polynomial");
        System.out.println("6. Quit");
        System.out.println("------------------------------------------------------");
    }

    /**
     * Handles main interface for evaluating/solving a polynomial. Used in text based execution.
     * @throws Exception for invalid polynomial inputs.
     */
    public void evaluatePolynomialText() throws Exception{
        System.out.println("You want to evaluate a polynomial.");

        Polynomial p = readPolynomial();
        System.out.println("The polynomial entered is " + p.toString());
        System.out.print("What is the value to be assigned to variable of the polynomial? ");
        double value= readDouble();
        System.out.println("The polynomial evaluates to : "+ p.evaluate(value));
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Handles data validation for allowing user to input an integer/number in the text based execution.
     * @param low is the lowest allowed user input.
     * @param high is the highest allowed user input.
     * @return the integer/number that the user entered.
     */
    private int readInteger(int low, int high){
        boolean validInput = false;
        int value=0;
        while (!validInput){
            try{
                value = Integer.parseInt(keyboard.nextLine());
                if ( value < low){
                    System.out.print("The number must not be lower than "+ low + ". ");
                }
                else if ( value > high){
                    System.out.print("The number must not be greater than "+ high +". ");
                } else {
                    validInput = true;
                }
            } catch (Exception x){
                System.out.println("You have to enter an integer from " + low + " to " + high + ".");
            }
        }
        return value;
    }

    /**
     * Allow user to input a double/decimal value. Used in the text based execution.
     * @return a double type variable that the user entered.
     */
    private double readDouble(){
        boolean validInput = false;
        double value=0;
        while (!validInput){
            try{
                value = Double.parseDouble(keyboard.nextLine());
                validInput = true;
            } catch (Exception x){
                System.out.println("You have to enter a number.");
            }
        }
        return value;
    }

    /**
     * Allows user to input a polynomial. Used in the text based execution
     * <p>
     *     Prints prompt messages and allows user to input each value separately. Values that the user has to input are the following:
     *     1. Literal/Variable
     *     2. Degree/Exponent
     *     3. Coefficient
     * </p>
     * @return the polynomial built based on user inputs.
     * @throws Exception for invalid user input.
     */
    public Polynomial readPolynomial() throws Exception{
        Polynomial p = new Polynomial();
        int degree=-1;
        boolean validDegree = false;
        char literalCoefficient = 'x';
        System.out.println("The polynomial should involve one variable/literal only.");
        do {
            System.out.print("What is the literal coefficient of the polynomial in one variable? ");

            literalCoefficient = keyboard.nextLine().charAt(0);
        } while (!Character.isAlphabetic(literalCoefficient));
        do {
            System.out.print("What is the degree of the polynomial? ");
            degree = readInteger(Integer.MIN_VALUE,Integer.MAX_VALUE);
            validDegree = true;
        } while (!validDegree);
        for (int x=degree; x>=0; x=x-1){
            Term term = readTerm(literalCoefficient, x);
            p.addTerm(term);
        }
        return p;
    }

    /**
     * Builds a term based on the given literal and degree. Used in the text based execution
     * <p>
     *     Allows user to input the coefficient for the term, attaching it to the given literal and degree.
     *     to create a new term.
     * </p>
     * @param literal is the literal to be used to create a new term.
     * @param degree is the degree to be used to create a new term.
     * @return a new term with the given literal and degree along with the user input coefficient.
     * @throws Exception for invalid user inputs.
     */
    public Term readTerm(char literal, int degree)throws Exception{
        double nCoeff=0;
        System.out.print("Enter the numerical coefficient of the term with degree " + degree +": ");
        nCoeff = readDouble();
        Term term = new Term(nCoeff, literal, degree);
        return term;
    }

    /**
     * Allows user to add 2 Polynomials. Used in text based execution.
     * <p>
     *     Asks user for 2 polynomials.
     *     Uses the readPolynomial method to allow user to input details for the polynomial.
     *     Prints polynomials to be added.
     *     Prints sum of 2 given polynomials.
     * </p>
     * @throws Exception for invalid user inputs.
     */
    public void addPolynomials() throws Exception {
        System.out.println("You want to add two polynomials.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second variable should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();
        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());
        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Sum of the polynomials : " + p1.add(p2));
        } else {
            System.out.println("The two polynomials cannot be added because they have different literals.");
        }
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Allows user to subtract a polynomial from another polynomial. Used in text based execution.
     * <p>
     *     Asks user for 2 polynomials.
     *     Uses the readPolynomial method to allow user to input details for the polynomial.
     *     Prints polynomials to be subtracted.
     *     Prints difference of 2 given polynomials.
     * </p>
     * @throws Exception for invalid user inputs.
     */
    public void subtractPolynomials() throws Exception {
        System.out.println("You want to perform subtraction between two polynomials.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second variable should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();
        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());
        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Difference of the polynomials : " + p1.subtract(p2));
        } else {
            System.out.println("The second polynomial cannot be subtracted form the first polynomial because they have different literals.");
        }
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Allows user to multiply 2 Polynomials. Used in text based execution.
     * <p>
     *     Asks user for 2 polynomials.
     *     Uses the readPolynomial method to allow user to input details for the polynomial.
     *     Prints polynomials to be multiplied.
     *     Prints product of 2 given polynomials.
     * </p>
     * @throws Exception for invalid user inputs.
     */
    public void multiplyPolynomials() throws Exception {
        System.out.println("You want to perform multiplication between two polynomials.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second variable should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();
        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());
        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Product of the polynomials : " + p1.multiply(p2));
        } else {
            System.out.println("The polynomials cannot be multiplied because they have different literals.");
        }
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Allows user to divide a polynomial by another polynomial. Used in text based execution.
     * <p>
     *     Asks user for 2 polynomials.
     *     Uses the readPolynomial method to allow user to input details for the polynomial.
     *     Prints polynomials to be divided.
     *     Prints quotient of 2 given polynomials.
     * </p>
     * @throws Exception for invalid user inputs.
     */
    public void dividePolynomials() throws Exception {
        System.out.println("You want to perform division between two polynomials.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second variable should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();
        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());
        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Quotient of the polynomials : " + p1.add(p2));
        } else {
            System.out.println("The first polynomials cannot be divided by the second polynomial because they have different literals.");
        }
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

//    Main method to run text based program
//    public static void main(String[] args) {
//        PolynomialArithmetic program;
//        try {
//            program = new PolynomialArithmetic();
//            program.run();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    } // end of main
}
