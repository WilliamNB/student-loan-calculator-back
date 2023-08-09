package studentLoan.calculator.v4;
import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class LoanCalculator {

    @GetMapping("/{salary}/{startBalance}/{loanType}/{additionalPayments}/{salaryIncrease}")
    public ResponseEntity<ArrayList<Result>> route1(@PathVariable int salary, @PathVariable int startBalance, @PathVariable LoanType loanType, @PathVariable int additionalPayments, @PathVariable int salaryIncrease){
        return generateResults(salary, startBalance, loanType, additionalPayments, salaryIncrease, "");
    }

    @GetMapping("/{salary}/{startBalance}/{loanType}/{additionalPayments}/{salaryIncrease}/{futureSalary}")
    public ResponseEntity<ArrayList<Result>> route2(@PathVariable int salary, @PathVariable int startBalance, @PathVariable LoanType loanType, @PathVariable int additionalPayments, @PathVariable int salaryIncrease, @PathVariable String futureSalary){
        return generateResults(salary, startBalance, loanType, additionalPayments, salaryIncrease, futureSalary);
    }
    
    public ResponseEntity<ArrayList<Result>> generateResults(int salary, int startBalance, LoanType loanType, int additionalPayments, int salaryIncrease, String futureSalary){


        Person person = new Person(salary, startBalance, salaryIncrease, additionalPayments, loanType);
        Loan loan = person.getLoan();

        if(futureSalary != ""){
            String[] futureSalaries = futureSalary.split("-");

            for(int i = 0; i < futureSalaries.length; i = i+2){
                person.setFutureSalaries(Integer.parseInt(futureSalaries[i]), Integer.parseInt(futureSalaries[i+1]));
            }
        }

        double balance = person.getStartBalance();
        int years = 0;
        ArrayList<Result> results = new ArrayList<Result>();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        while (balance > 0){
            if(years >= loan.getWriteOffYear()){
                break;
            }else{
                double currentSalary = person.getSalary();
                double paidSalary = ((person.getSalary() - loan.getRepayThreshold()) * loan.getPaymentPercentage());
                int paidAdditional = person.getAdditionalPayments();
                double interest = balance * loan.getInterestRate();

                double interestPaid = calucalteInterestPaid(paidSalary, paidAdditional, interest);

                balance -= (paidSalary + paidAdditional);
                years ++;
                year ++;

                Result result = new Result(year, balance, paidSalary, paidAdditional, currentSalary, interestPaid);
                if (!results.isEmpty()){
                    result.updateTotalPaid(results.get(years - 2).getTotalPaid());
                    result.updateTotalInterestPaid(results.get(years - 2).getTotalPaidInterest());
                }
                results.add(result);

                balance += (interest);
                person.increaseSalary();
                person.checkFutureSalaries(year);
                System.out.println("Salary: " + person.getSalary() + ", year: " + year);
            }
        }

        for(Result i : results){
            System.out.println("Balance: " + i.getRemainingBalance() + ", Years: " + i.getYearNumber() + ", Salary: " + i.getSalary() + ", Payed this year total: " + i.getPaidThisYearTotal() + ", Payed this year salary: " + i.getPaidThisYearSalary() + ", Payed in total: " + i.getTotalPaid()  + ", Payed interest this year: " + i.getTotalPaidInterest()); 
        }
        String test = ("It will take you " + years + " to repay your student loan");

        return new ResponseEntity<ArrayList<Result>>(results, HttpStatus.OK);
    }

    public double calucalteInterestPaid(double paidSalary, int paidAdditional, double interest ){
        double interestPaid;

        if((paidSalary + paidAdditional) > interest){
            interestPaid = interest;
        }else{
            interestPaid = interest - paidSalary + paidAdditional;
        }

        return interestPaid;
    }



    @GetMapping("/{salary}/{startBalance}")
    public ResponseEntity<Integer> testing(@PathVariable int salary, @PathVariable int startBalance){
        String test = "salary from url: " + salary + "  balance from url: " + startBalance;
        int test1 = salary + startBalance;
        return new ResponseEntity<Integer>(test1, HttpStatus.OK);
    }

    @GetMapping("/{loan}")
    public ResponseEntity<LoanType> customTypes(@PathVariable LoanType loan){
        return new ResponseEntity<LoanType>(loan, HttpStatus.OK);
    }
    
}
