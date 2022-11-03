import entities.Employee;

import javax.persistence.Persistence;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    private static final String DATABASE_NAME = "soft_uni";
    private static String SELECT_EMPL = "SELECT e FROM Employee AS e WHERE e.firstName LIKE :r";
    private static String PRINT_FORM = "%s %s - %s - ($%.2f)%n";

    public static void main(String[] args) {

        String firstTwoSim = new Scanner(System.in).nextLine();

        Persistence.createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager()
                .createQuery(SELECT_EMPL, Employee.class)
                .setParameter("r", firstTwoSim+"%")
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_FORM, e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));
    }
}

