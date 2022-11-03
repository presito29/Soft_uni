import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class GetEmpWithProject {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String SELECT_EMPL = "select e from Employee e where e.id = :id";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);

        final EntityManager entityManager = entityManagerFactory.createEntityManager();


        final int emID = new Scanner(System.in).nextInt();

        System.out.println(entityManager.createQuery(SELECT_EMPL, Employee.class)
                .setParameter("id", emID)
                .getSingleResult()
                .toString());

        entityManager.close();
    }
}
