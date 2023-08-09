package studentLoan.calculator.v4;
import java.util.HashMap;

public class Person {
    private int salary;
    private double startBalance;
    private Loan loan;
    private double yearlySalaryIncrease;
    private int additionalPayments;
    private HashMap<Integer, Integer> futureSalaries;

    public Person(int salary, double startBalance, double yearlySalaryIncrease, int additionalPayments, LoanType loneType){
        this.salary = salary;
        this.startBalance = startBalance;
        this.yearlySalaryIncrease = yearlySalaryIncrease / 100;
        this.additionalPayments = additionalPayments;
        loan = new Loan(loneType);
        futureSalaries = new HashMap<Integer, Integer>();
    }

    public Person(int salary, double startBalance){
        this.salary = salary;
        this.startBalance = startBalance;
    }

    public int getSalary(){
        return salary;
    }

    public void setSalary(int value){
        this.salary = value;
    }

    public double getStartBalance(){
        return startBalance;
    }

    public int getAdditionalPayments(){
        return this.additionalPayments;
    }

    public void increaseSalary(){
        setSalary(salary += (salary * yearlySalaryIncrease));
    }

    public Loan getLoan(){
        return loan;
    }

    public void setFutureSalaries(int year, int value){
        futureSalaries.put(year, value);
    }

    public void checkFutureSalaries(int year){
        if(futureSalaries.containsKey(year)){
            setSalary(futureSalaries.get(year));
        }
    }
}

