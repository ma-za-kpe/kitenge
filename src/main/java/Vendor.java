import org.sql2o.Connection;

public class Vendor extends Users {

    public String productCategory;
    //CONSTANTS
    public static final String ROLE = "vendor";

    public Vendor(String productCategory, String name, String role) {
        this.productCategory = productCategory;
        this.name = name;
        this.role = role;
    }

    public String getProductCategory() {
        return productCategory;
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
        return super.getRole();
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
            String sql = "INSERT INTO animals (name, type, sightingid, daterecorded) VALUES (:name, :type, :sightingId, now());";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .addParameter("sightingId", this.sightingId)
                    .addParameter("dateRecorded", this.dateRecorded)
                    .executeUpdate()
                    .getKey();
        }
    }
}
