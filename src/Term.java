/**
 * Template for a term in the Polynomial class
 * <p>
 *     The following class is a template for a term of an algebraic polynomial that involves only one literal.
 *     3x^2 is an example of a term
 *     where 3 is the numerical coefficient,
 *     x is the literal coefficient and
 *     2 is the degree
 * </p>
 */
public class Term implements Comparable<Term> {
    private double coefficient; //data member to hold numerical coefficient of a term
    private int degree; // data member to hold the degree of a term
    private char literal; // data member to hold the literal coefficient of a term

    /**
     * Constructs a term with coefficient set to 0, degree set to 0 and literal set to x.
     */
    public Term() {
        coefficient = 0;
        degree = 0;
        literal = 'x';
    }

    /**
     * Constructs a term that sets the coefficient, literal and degree
     * based on given the coefficient, literal and degree
     */
    public Term(double coef, char literal, int degree) {
        this.coefficient = coef;
        this.literal = literal;
        this.degree = degree;
    }

    // sample use
    // Term t = new Term(2.0, 'x', 3)

    /**
     * Sets the value of the numerical coefficient of this term to the specified coefficient
     */
    public void setCoefficient(double coef) {
        this.coefficient= coef;
    }

    // sample use
    // t.setCoefficient(3)

    /**
     * Sets the value of the literal coefficient of this term to the specified character literal
     */
    public void setLiteral(char literal){
        this.literal = literal;
    }

    // t.setLiteral('y')

    /**
     * Sets the value of the degree of this term to a specified degree
     */
    public void setDegree(int degree) {
        this.degree = degree;
    }

    /**
     * Returns the numerical coefficient of this term
     */
    public double getCoefficient() {
        return this.coefficient;
    }

    // System.out.println("Coefficient=" + t.getCoefficient())

    /**
     * Returns the literal coefficient of this term
     */
    public char getLiteral() {
        return this.literal;
    }

    /**
     * Returns the degree of this term
     */
    public int getDegree() {
        return this.degree;
    }

    /**
     Returns 0 if the degree of this term is equal to the degree of another term
     else returns an integer that is greater than 0 if the degree of this term is
     greater than the degree of another term else returns an integer that is lesser than
     0 if the degree of this term is lower than the degree of another term.
     */
    public int compareTo(Term another){
        if (this.getDegree() == another.getDegree())
            return 0;
        else
        if (this.getDegree() > another.getDegree())
            return -1;
        else
            return 1;

        //sample use
        // return this.toString().compareTo(another.toString());
    }

    // Term t1 = new Term()
    // Term t2 = new Term(1,'x', 1)
    // ArrayList<Term> p = new ArrayList();
    // ...
    // p has been assigned several terms
    // Collections.sort(p)

    /**
     Returns a string representation of the term
     that follows a format with the example 3x^2
     */
    public String toString() {
        if (coefficient == 0 )
            return "";
        else
        if (coefficient==1 && degree == 1)
            return ""+literal;
        else

        if (coefficient==1 && degree != 1)
            return "" + literal + "^" + degree;
        else
            return (coefficient + literal + "^" + degree);
    }
}
