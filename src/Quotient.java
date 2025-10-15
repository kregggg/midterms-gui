/**
 * A template for a quotient, result of division operation in polynomials
 */
public class Quotient {
    Polynomial quotientP;
    Polynomial remainderP;

    /**
     * Default constructor of Quotient
     */
    public Quotient(){
        quotientP = null;
        remainderP = null;
    }

    /**
     * Allow user to set value to the quotient variable
     * @param q is to be set as the quotient
     */
    public void setQuotientP(Polynomial q){
        quotientP = q;
    }

    /**
     * Allow user to set a value to the remainder variable
     * @param p is to be set as the remainder
     */
    public void setRemainderP(Polynomial p){
        remainderP = p;
    }

    /**
     * Allow user to get the value of the quotient
     * @return value of quotient
     */
    public Polynomial getQuotientP() {
        return quotientP;
    }

    /**
     * Allow user to get remainder of the program.
     * @return the value of the remainder
     */
    public Polynomial getRemainderP() {
        return remainderP;
    }

    /**
     * Convert quotient into a string for printing
     * @return a string value that contains the results along with corresponding labels
     */
    public String toString() {
        return (" Quotient: " + quotientP.toString() + " Remainder: " + remainderP.toString());
    }
}