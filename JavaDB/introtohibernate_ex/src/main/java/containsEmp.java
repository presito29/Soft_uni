import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class containsEmp {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String SELECT_COUNT_EMPL = "select count(e) from Employee e where e.firstName = :fn and  e.lastName = :ln" ;

    public static void main(String[] args) {
      final  EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);

       final EntityManager entityManager = entityManagerFactory.createEntityManager();

   final String [] name = new Scanner(System.in).nextLine().split(" ");

        String firstName = name[0];
        String lastName = name[0];

       final Long countMatch = entityManager.createQuery
                        ( SELECT_COUNT_EMPL, Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();

        if (countMatch == 0){
            System.out.println("NO");
        }else {
            System.out.println("YES");
        }
    }
}
