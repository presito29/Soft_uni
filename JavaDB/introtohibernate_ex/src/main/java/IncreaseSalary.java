import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IncreaseSalary {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String SELECT_EMPL_ORDERBY_DEPNAME= "select e from Employee e where e.department.name in ('Engineering' , 'Tool Design', 'Marketing' , 'Information Services')";
    private static final String UPDATE_EMPL = "update Employee e set e.salary = e.salary * 1.12 where e.department.id in (1 , 2, 4 , 11)";
    private static final String FORMAT = "%s %s ($%.2f)";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);

        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(UPDATE_EMPL, Employee.class)
                        .executeUpdate();

        entityManager.getTransaction().commit();


        entityManager.createQuery(SELECT_EMPL_ORDERBY_DEPNAME, Employee.class)
                        .getResultList()
                                .forEach(e ->System.out.printf(FORMAT
                                        ,e.getFirstName()
                                        ,e.getLastName()
                                        ,e.getSalary()
                                ));

        entityManager.close();
    }

}
