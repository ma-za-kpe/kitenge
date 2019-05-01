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
}
