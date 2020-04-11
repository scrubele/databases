import com.scrubele.ArtistsEntity;
import com.scrubele.OrganizationsEntity;
import com.scrubele.ProjectsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TimeZone;

public class Main {

    private static final SessionFactory ourSessionFactory;


    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void printValues(Session session) {
        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {

        }
    }
    public static void printJoinTable(Session session){
        session.beginTransaction();
        final Query query = session.createQuery("from ArtistsEntity");
        System.out.println("executing: " + query.getQueryString());
        for (Object o : query.list()) {
            ArtistsEntity artistsEntity = (ArtistsEntity) o;
            System.out.println("  " + artistsEntity.toStringJoinTable());
        }

    }
    public static void addToJoinTable(Session session){
        session.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Input an id of artist: ");
        int artist_id = input.nextInt();
        System.out.println("Input an id of project: ");
        int projects_id = input.nextInt();

        Query query1 = session.createQuery("from ArtistsEntity  where id= :artist_id");
        query1.setParameter("artist_id", artist_id);
        ArtistsEntity artistsEntity = (ArtistsEntity) query1.list().get(0);
        Query query2 = session.createQuery("from ProjectsEntity  where id= :projects_id");
        query2.setParameter("projects_id", projects_id);
        ProjectsEntity projectsEntity = (ProjectsEntity) query2.list().get(0);
        artistsEntity.addprojectsEntity(projectsEntity);
        session.getTransaction().commit();
        System.out.println("Added to join table.");
        //artistsEntity.setProjects();
    }

    public static void deleteFromJoinTable(Session session){
        session.beginTransaction();
        Scanner input = new Scanner(System.in);
        System.out.println("Input an id of artist: ");
        int artist_id = input.nextInt();
        System.out.println("Input an id of project: ");
        int projects_id = input.nextInt();

        Query query1 = session.createQuery("from ArtistsEntity  where id= :artist_id");
        query1.setParameter("artist_id", artist_id);
        ArtistsEntity artistsEntity = (ArtistsEntity) query1.list().get(0);
        Query query2 = session.createQuery("from ProjectsEntity  where id= :projects_id");
        query2.setParameter("projects_id", projects_id);
        ProjectsEntity projectsEntity = (ProjectsEntity) query2.list().get(0);
        artistsEntity.deleteProjectEnitity(projectsEntity);
        session.getTransaction().commit();
        System.out.println("Deleted from join table.");
        //artistsEntity.setProjects();
    }
    private static void insertDataArtist(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new artist: ");
        String newArtist_name = input.next();
        String newArtist_surname = input.next();
        String newArtist_position = input.next();
        int id_organization = input.nextInt();

        OrganizationsEntity organizationsEntity = new OrganizationsEntity(id_organization);

        session.beginTransaction();
        ArtistsEntity artistsEntity = new ArtistsEntity(newArtist_name, newArtist_surname,
                newArtist_position, organizationsEntity);
        session.save(artistsEntity);
        session.getTransaction().commit();

        System.out.println("end insert artist");


    }
    @Transactional
    private static void deleteDataArtist(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input id of artists for delete: ");
        int artist_id = input.nextInt();

        session.beginTransaction();
        Query query = session.createQuery("delete ArtistsEntity where id=:artist_id");
        query.setParameter("artist_id", artist_id);

        int result = query.executeUpdate();
        session.getTransaction().commit();

        System.out.println("end delete artist");
    }

    private static void updateDataArtist(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input id  of artist what you want to update: ");
        Integer artist_id = input.nextInt();
        System.out.println("Input new surname of artist for %s: "+ artist_id);
        String surname_new = input.next();

        session.beginTransaction();
        Query query = session.createQuery("update ArtistsEntity SET surname=:surname_new " +
                " where id=:artist_id");
        query.setParameter("artist_id", artist_id);
        query.setParameter("surname_new", surname_new);

        int result = query.executeUpdate();
        session.getTransaction().commit();
        System.out.println("Updated.");

    }

    public static void main(final String[] args) throws Exception {
        System.out.println(TimeZone.getDefault());
        final Session session = getSession();

        Scanner input = new Scanner(System.in);
        int x = 10000;
        while (x != 0) {
            System.out.println("Enter 1-readData(),\n 2 -insertDataArtist()," +
                    "\n 3 - deleteDataArtist(),\n 4 - updateDataArtist(), \n 5 - print joinTable()," +
                    "\n 6 - added record to joinTable(),\n 7 - delete record from joinTable(),\n0 - exit");
            x = input.nextInt();
            switch (x) {
                case 1: {
                    printValues(session);
                    break;
                }
                case 2: {
                    insertDataArtist(session);
                    break;
                }
                case 3: {
                    deleteDataArtist(session);
                    break;
                }
                case 4: {
                     updateDataArtist(session);
                    break;
                }
                case 5: {
                        printJoinTable(session);
                        break;
                }
                case 6: {
                    addToJoinTable(session);
                    break;
                }
                case 7: {
                    deleteFromJoinTable(session);
                    break;
                }
            }
        }
    }
}