package integration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

public class ContainerizedDbIntegrationTest {

    private static final int PORT = 3307;
    private static final String PASSWORD = "holdkrykke";
    private static final String USER = "root";

    protected String getDbUser() {
        return USER;
    }

    protected String getDbPassword() {
        return PASSWORD;
    }

    protected String getDbUrl(){ return "jdbc:mysql://127.0.0.1:" + PORT + "/";}

    protected String getDb() {
        return "BookingSystemTest";
    }

    protected String getConnectionString() {
        return getDbUrl() + getDb();
    }

    protected void runMigration(double level) {
        String url = getDbUrl();
        String db = getDb();
        Flyway flyway = new Flyway(
                new FluentConfiguration()
                        .defaultSchema(db)
                        .createSchemas(true)
                        .schemas(db)
                        .target(Double.toString(level))
                        .dataSource(url, USER, PASSWORD));
        flyway.clean();
        flyway.repair();
        flyway.migrate();
    }

}
