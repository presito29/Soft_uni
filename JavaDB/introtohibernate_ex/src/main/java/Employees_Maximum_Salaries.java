import javax.persistence.Persistence;

public class Employees_Maximum_Salaries {
    private static final String DATABASE_NAME = "soft_uni";
   private static String SELECT_MAX_SALARY = "SELECT e.department.name, MAX(e.salary) FROM Employee AS e GROUP BY e.department.id HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000";

    public static void main(String[] args) {
        Persistence
                .createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager()
                .createQuery(SELECT_MAX_SALARY, Object[].class)
                .getResultList()
                .forEach(p -> System.out.println(p[0] + " " + p[1]));
    }
}
