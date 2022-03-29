package market.connection;

public class JpaConnectionFactory {

    private EntityManagerFactory factory;

    public JpaConnectionFactory() {
        this.factory = Persistence.createEntityManagerFactory("market");
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
