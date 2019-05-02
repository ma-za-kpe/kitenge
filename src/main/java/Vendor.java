import org.sql2o.Connection;

import java.util.List;

public class Vendor extends Users {

    //CONSTANTS
    public static final String ROLE = "vendor";

    public Vendor(String name, String role) {
        this.name = name;
        this.role = ROLE;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getRole() {
        return ROLE;
    }

    @Override
    public boolean equals(Object otherVendor){
        if (!(otherVendor instanceof Vendor)) {
            return false;
        } else {
            Vendor newVendor= (Vendor) otherVendor;
            return this.getName().equals(newVendor.getName());
        }
    }

    //save vendor into db
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO users (name, role) VALUES (:name, :role);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("role", this.role)
                    .executeUpdate()
                    .getKey();
        }
    }

    //get all vendors
    public static List<Vendor> all() {
        String sql = "SELECT * FROM users WHERE role='vendor';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Vendor.class);
        }
    }

    //find animals by id
    public static Vendor find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM users where id=:id";
            Vendor vendor = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Vendor.class);
            return vendor;
        }
    }

    //find vendor by id
    public static Vendor findVendorByName(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT id FROM users where name=:name";
            Vendor vendor = con.createQuery(sql)
                    .addParameter("name", name)
                    .executeAndFetchFirst(Vendor.class);
            return vendor;
        }
    }


    //
    //update an Vendor
    public void update(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE users SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    //delete Vendor
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM users WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
