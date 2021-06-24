package inventory.system.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product_categories")
public class ProductCategory {
    @Id
    private String id;
    private String name;
    private int is_can_be_stale;
    private String status;
    private String created_by;
    private Date created_at;
    private String updated_by;
    private Date updated_at;

    public ProductCategory() {
    }

    public ProductCategory(String id, String name, int is_can_be_stale, String status, String created_by, Date created_at, String updated_by, Date updated_at) {
        this.id = id;
        this.name = name;
        this.is_can_be_stale = is_can_be_stale;
        this.status = status;
        this.created_by = created_by;
        this.created_at = created_at;
        this.updated_by = updated_by;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIs_can_be_stale() {
        return is_can_be_stale;
    }

    public void setIs_can_be_stale(int is_can_be_stale) {
        this.is_can_be_stale = is_can_be_stale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
