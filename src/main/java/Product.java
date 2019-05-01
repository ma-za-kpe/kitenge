import org.sql2o.Connection;

import java.util.List;

public class Product extends Users{

    private int price;
    private String imageUrl;
    private int userId;

    public Product(String name, int price, String imageUrl, int userId) {
        this.name = name;
        this.price = price;
        this.imageUrl =imageUrl;
        this.userId = userId;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object otherProduct){
        if (!(otherProduct instanceof Product)) {
            return false;
        } else {
            Product newProduct= (Product) otherProduct;
            return this.getName().equals(newProduct.getName());
        }
    }

    //save Product into db
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO product (name, price, imageurl, userid) VALUES (:name, :price, :imageurl, :userid);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("price", this.price)
                    .addParameter("imageurl", this.imageUrl)
                    .addParameter("userid", this.userId)
                    .executeUpdate()
                    .getKey();
        }
    }

    //get all Products
    public static List<Product> all() {
        String sql = "SELECT * FROM product ";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Product.class);
        }
    }

    //find animals by id
    public static Product find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM product where id=:id";
            Product Product = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Product.class);
            return Product;
        }
    }
    //
    //update an Product
    public void update(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE product SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    //delete Product
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM product WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}
