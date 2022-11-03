import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;

public class FindLatest10Projects {
    private static final String DATABASE_NAME = "soft_uni";

    public static String GET_PROJECT_BY_START = "SELECT p FROM Project AS p ORDER BY p.startDate DESC";
    public static String PRINT_FORMAT = "Project name: %s%n" +
            "        Project Description: %s%n" +
            "        Project Start Date:%s%n" +
            "        Project End Date: %s%n";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory(DATABASE_NAME);

        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery(GET_PROJECT_BY_START, Project.class).setMaxResults(10).getResultList()
                .stream().sorted(Comparator.comparing(Project::getName))
                .forEach(project -> System.out.printf(PRINT_FORMAT,
                        project.getName(), project.getDescription(), project.getStartDate(), project.getEndDate()));

        em.getTransaction().commit();
        em.close();
    }
}
