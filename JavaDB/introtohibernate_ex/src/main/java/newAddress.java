import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class newAddress {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String UPDATE_EMPL = "Update Employee e set e.address = :newAddress where e.lastName = :ln" ;

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);

        final EntityManager entityManager = entityManagerFactory.createEntityManager();


        final String lastName = new Scanner(System.in).nextLine();

        entityManager.getTransaction().begin();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");

        entityManager.persist(newAddress);

        entityManager.createQuery(UPDATE_EMPL)
                .setParameter("newAddress", newAddress)
                .setParameter("ln", lastName)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
