import org.sql2o.Connection;

import java.util.List;

public class Kiosk extends Users {

    private static int userId;

    public Kiosk(String name, int userId) {
        this.name = name;
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

    public static int getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object otherKiosk){
        if (!(otherKiosk instanceof Kiosk)) {
            return false;
        } else {
            Kiosk newKiosk= (Kiosk) otherKiosk;
            return this.getName().equals(newKiosk.getName());
        }
    }

    //save Kiosk into db
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO kiosk (name, userid) VALUES (:name, :userId);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("userId", this.userId)
                    .executeUpdate()
                    .getKey();
        }
    }

    //get all Kiosks
    public static List<Kiosk> all() {
        String sql = "SELECT * FROM kiosk;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Kiosk.class);
        }
    }

    //find kiosk by id
    public static Kiosk find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM kiosk where id=:id";
            Kiosk Kiosk = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Kiosk.class);
            return Kiosk;
        }
    }

    //
    //update an Kiosk
    public void update(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE kiosk SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    //delete Kiosk
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM kiosk WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    
}
