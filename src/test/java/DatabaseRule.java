import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/kitenge_test", "maku", "maku");
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteUsersQuery = "DELETE FROM users *;";
            String deleteProductQuery = "DELETE FROM product *;";
            String deleteKioskQuery = "DELETE FROM kiosk *;";
            String deleteCartQuery = "DELETE FROM cart *;";
            con.createQuery(deleteUsersQuery).executeUpdate();
            con.createQuery(deleteProductQuery).executeUpdate();
            con.createQuery(deleteKioskQuery).executeUpdate();
            con.createQuery(deleteCartQuery).executeUpdate();
        }
    }

}
