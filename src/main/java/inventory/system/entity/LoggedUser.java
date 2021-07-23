package inventory.system.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class LoggedUser {
    private Integer id;
    private String name;
    private Integer role_id;
    private String role_name;
    private String warehouse_id;
    private String warehouse_name;
    private Integer is_branch;
    private String status;

    public LoggedUser() {
    }

    public LoggedUser(Integer id, String name, Integer role_id, String role_name, String warehouse_id, String warehouse_name, String status) {
        this.id = id;
        this.name = name;
        this.role_id = role_id;
        this.role_name = role_name;
        this.warehouse_id = warehouse_id;
        this.warehouse_name = warehouse_name;
        this.status = status;
    }

    public Integer getIs_branch() {
        return is_branch;
    }

    public void setIs_branch(Integer is_branch) {
        this.is_branch = is_branch;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
