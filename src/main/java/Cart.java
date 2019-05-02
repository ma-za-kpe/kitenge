import org.sql2o.Connection;

import java.util.List;

public class Cart extends Users{

    private int price;
    private int userId;

    public Cart(String name, int price, int userId) {
        this.name = name;
        this.price = price;
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

    public int getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object otherCart){
        if (!(otherCart instanceof Cart)) {
            return false;
        } else {
            Cart newCart= (Cart) otherCart;
            return this.getName().equals(newCart.getName());
        }
    }

    //save Cart into db
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO cart (name, price, userid) VALUES (:name, :price, :userid);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("price", this.price)
                    .addParameter("userid", this.userId)
                    .executeUpdate()
                    .getKey();
        }
    }

    //get all Carts
    public static List<Cart> all() {
        String sql = "SELECT * FROM cart ";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Cart.class);
        }
    }

    //find animals by id
    public static Cart find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM cart where id=:id";
            Cart Cart = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Cart.class);
            return Cart;
        }
    }
    //
    //update an Cart
    public void update(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE cart SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    //delete Cart
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM cart WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}
