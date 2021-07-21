package inventory.system.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    private String origin_id;
    private String dest_id;
    private String origin_type;
    private String dest_type;
    private Date date;
    private int driver_id;

    private Date created_at;
    private String created_by;

    private Date approved_at;
    private String approved_by;

    private Date checked_at;
    private String checked_by;

    private String updated_by;
    private Date updated_at;

    private int status_order_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "origin_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Warehouses originList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dest_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Warehouses destList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(String origin_id) {
        this.origin_id = origin_id;
    }

    public String getDest_id() {
        return dest_id;
    }

    public void setDest_id(String dest_id) {
        this.dest_id = dest_id;
    }

    public String getOrigin_type() {
        return origin_type;
    }

    public void setOrigin_type(String origin_type) {
        this.origin_type = origin_type;
    }

    public String getDest_type() {
        return dest_type;
    }

    public void setDest_type(String dest_type) {
        this.dest_type = dest_type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getApproved_at() {
        return approved_at;
    }

    public void setApproved_at(Date approved_at) {
        this.approved_at = approved_at;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public Date getChecked_at() {
        return checked_at;
    }

    public void setChecked_at(Date checked_at) {
        this.checked_at = checked_at;
    }

    public String getChecked_by() {
        return checked_by;
    }

    public void setChecked_by(String checked_by) {
        this.checked_by = checked_by;
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

    public int getStatus_order_id() {
        return status_order_id;
    }

    public void setStatus_order_id(int status_order_id) {
        this.status_order_id = status_order_id;
    }

    public Warehouses getOriginList() {
        return originList;
    }

    public void setOriginList(Warehouses originList) {
        this.originList = originList;
    }

    public Warehouses getDestList() {
        return destList;
    }

    public void setDestList(Warehouses destList) {
        this.destList = destList;
    }
}
