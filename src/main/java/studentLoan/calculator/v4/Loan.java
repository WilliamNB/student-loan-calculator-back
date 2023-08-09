package studentLoan.calculator.v4;

public class Loan {

    private LoanType loanType;
    private double interestRate;
    private double paymentPercentage;
    private int repayThreshold;
    private int writeOffYear;

    public Loan(double interestRate, double paymentPercentage, int repayThreshold){
        this.interestRate = interestRate;
        this.paymentPercentage = paymentPercentage;
        this.repayThreshold = repayThreshold;
    }

    public Loan(LoanType loanType){
        this.loanType = loanType;
        switch (loanType){
            case ONE : 
                interestRate = 0.05 ;
                paymentPercentage = 0.09;
                repayThreshold = 22015;
                writeOffYear = 25;
            break;

            case TWO :  
                interestRate = 0.08 ;
                paymentPercentage = 0.09;
                repayThreshold = 27297;
                writeOffYear = 30;
            break;

            case FOUR : 
                interestRate = 0.05 ;
                paymentPercentage = 0.09;
                repayThreshold = 27660;
                writeOffYear = 30;
            break;

            case FIVE : 
                interestRate = 0.1 ;
                paymentPercentage = 0.09;
                repayThreshold = 25000;
                writeOffYear = 40;
            break;
        }
    }

    public double getInterestRate(){
        return interestRate;
    }

    public double getPaymentPercentage(){
        return paymentPercentage;
    }

    public int getRepayThreshold(){
        return repayThreshold;
    } 

    public int getWriteOffYear(){
        return writeOffYear;
    }

    public LoanType getLoanType(){
        return loanType;
    }
}

