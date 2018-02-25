package tiny;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private int userId;

    @Column(name = "name")
    private String name;


    @Column(name = "type")
    private String type;

    public User() {
        super();
    }



    public int getUserid() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}


