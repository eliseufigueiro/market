package market.application;

import market.connection.JpaConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Program {

    private static final Logger LOG = LogManager.getLogger(Program.class);

    public static void main(String[] args) {

        LOG.info("Log4j for Hello World!");

        new JpaConnectionFactory().getEntityManager()
    }
}
