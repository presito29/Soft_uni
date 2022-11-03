import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    private static final String DATABASE_NAME = "soft_uni";
    private static String SELECT_ADDRESS = "SELECT a FROM Address AS a WHERE a.town.name = :tName";

    private static String SELECT_TOWN = "SELECT t FROM Town AS t WHERE t.name = :tName";


    public static void main(String[] args) {

        String townInput = new Scanner(System.in).nextLine();
        EntityManager entityManager = Persistence
                .createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        entityManager.getTransaction().begin();

        try {

            List<Address> addresses = entityManager.
                    createQuery(SELECT_ADDRESS, Address.class).
                    setParameter("tName", townInput)
                    .getResultList();


            addresses.forEach(a -> {
                a.getEmployees().forEach(e -> e.setAddress(null));
                entityManager.remove(a);
            });

            Town town = entityManager.createQuery(SELECT_TOWN, Town.class)
                    .setParameter("tName", townInput)
                    .getSingleResult();
            entityManager.remove(townInput);

            entityManager.getTransaction().commit();

            if (addresses.size() == 1){
                System.out.println("1 address in" + town + " deleted");
            }else {
                System.out.println(addresses.size() + " addresses in " + town + " deleted");
            }
        } catch (Exception exception) {
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
    }
}
