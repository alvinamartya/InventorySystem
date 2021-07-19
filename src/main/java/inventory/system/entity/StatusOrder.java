package inventory.system.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "status_order")
public class StatusOrder {

    @Id
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
