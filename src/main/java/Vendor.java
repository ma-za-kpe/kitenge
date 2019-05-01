public class Vendor extends Users {

    public String productCategory;

    public Vendor(String productCategory) {
        this.productCategory = productCategory;
        this.
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
