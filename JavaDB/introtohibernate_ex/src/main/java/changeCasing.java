import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class changeCasing {
    private static final String DATABASE_NAME = "soft_uni";
   private static final String SELECT_TOWN = "SELECT t FROM Town t";


    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(DATABASE_NAME);

       final EntityManager entityManager = entityManagerFactory.createEntityManager();

       entityManager.getTransaction().begin();

     final Query towns = entityManager.createQuery(SELECT_TOWN, Town.class);

        final List<Town> resultList = towns.getResultList();

        for (Town town: resultList) {
            String townName = town.getName();
            if (townName.length() <= 5){
                town.setName(townName.toUpperCase());
                entityManager.persist(town);
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
