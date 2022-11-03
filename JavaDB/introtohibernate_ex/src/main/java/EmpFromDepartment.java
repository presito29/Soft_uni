import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmpFromDepartment {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String PRINT = "%s %s from %s - $%.2f%n";
    private static final String NAME_DEP = "Research and Developmen";
    private static final String SELECT_EMPL = "select e from Employee e " +
            "where e.department.name = :dp" +
            " order by e.salary, e.id" ;

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        String department = NAME_DEP;

        entityManager.createQuery(SELECT_EMPL, Employee.class)
                .setParameter("dp", department)
                .getResultList()
                .forEach(e -> System.out.printf(PRINT,
                        e.getFirstName(),
                        e.getLastName(),
                        e.getDepartment().getName(),
                        e.getSalary()));

        entityManager.close();
    }
}
