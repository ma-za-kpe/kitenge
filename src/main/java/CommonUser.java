import org.sql2o.Connection;

import java.util.List;

public class CommonUser extends Users {

    //CONSTANTS
    public static final String ROLE = "commonUser";

    public CommonUser(String name, String role) {
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
        return super.getRole();
    }

    @Override
    public boolean equals(Object otherCommonUser){
        if (!(otherCommonUser instanceof CommonUser)) {
            return false;
        } else {
            CommonUser newCommonUser= (CommonUser) otherCommonUser;
            return this.getName().equals(newCommonUser.getName());
        }
    }

    //save CommonUser into db
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

    //get all CommonUsers
    public static List<CommonUser> all() {
        String sql = "SELECT * FROM users WHERE role='commonUser';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(CommonUser.class);
        }
    }

    //find animals by id
    public static CommonUser find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM users where id=:id";
            CommonUser CommonUser = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(CommonUser.class);
            return CommonUser;
        }
    }
    //
    //update an CommonUser
    public void update(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE users SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    //delete CommonUser
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM users WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    
}
