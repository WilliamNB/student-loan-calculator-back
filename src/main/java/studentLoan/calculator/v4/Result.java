package studentLoan.calculator.v4;

public class Result {
    private int yearNumber;
    private double remainingBalance;
    private double paidThisYearSalary;
    private double paidThisYearAdditional;
    private double paidThisYearTotal;
    private double paidThisYearInterest;
    private double paidInterestTotal;
    private double totalPaid;
    private double salary;

    public Result(int yearNumber, double remainingBalance, double paidSalary, int paidAdditional, double salary, double interestPayed){
        this.yearNumber = yearNumber;
        this.remainingBalance = remainingBalance;
        this.paidThisYearSalary = paidSalary;
        this.paidThisYearAdditional = paidAdditional;
        this.paidThisYearTotal = paidSalary + paidAdditional;
        this.totalPaid = paidThisYearTotal;
        this.salary = salary;
        this.paidThisYearInterest = interestPayed;
        paidInterestTotal = interestPayed;
    }

    public Result(int yearNumber, double remainingBalance, double paidThisYear){
        this.yearNumber = yearNumber;
        this.remainingBalance = remainingBalance;
        this.paidThisYearSalary = paidThisYear;
        this.totalPaid = paidThisYear;
    }

    public int getYearNumber(){
        return this.yearNumber;
    }

    public void setYearNumber(int value){
        this.yearNumber = value;
    }

    public double getRemainingBalance(){
        return this.remainingBalance;
    }

    public void setRemainingBalance(double value){
        this.remainingBalance = value;
    }

    public double getTotalPaid(){
        return this.totalPaid;
    }

    public double getPaidThisYearSalary(){
        return this.paidThisYearSalary;
    }
    public double getPaidThisYearAdditional(){
        return this.paidThisYearAdditional;
    }
    public double getPaidThisYearInterest(){
        return this.paidThisYearInterest;
    }
    public double getPaidThisYearTotal(){
        return this.paidThisYearTotal;
    }

    public double getTotalPaidInterest(){
        return this.paidInterestTotal;
    }

    public double getSalary(){
        return this.salary;
    }

    public void setPaidThisYearSalary(double value){
        this.paidThisYearSalary = value;
    }

    public void setSalary(double value){
        this.salary = value;
    }

    public void updateTotalPaid(double lastYearsTotal){
        totalPaid += lastYearsTotal;
    }

    public void updateTotalInterestPaid(double lastYearsTotal){
        paidInterestTotal += lastYearsTotal;
    }
}
