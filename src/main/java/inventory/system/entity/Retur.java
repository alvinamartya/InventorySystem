package inventory.system.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "retur")
public class Retur {

    @Id
    private String id;
    private String origin_id;
    private String order_type;
    private String dest_id;
    private String dest_type;
    private Date date;
    private Integer driver_id;
    private Date created_at;
    private String created_by;
    private Date approved_at;
    private String approved_by;
    private Date checked_at;
    private String checked_by;
    private Date updated_at;
    private String updated_by;
    private String warehouse_at;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "origin_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Warehouses originList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dest_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Warehouses destList;

    public Retur(){

    }

    public Retur(String id, String origin_id, String order_type, String dest_id, String dest_type,
                 Date date, Integer driver_id, Date created_at, String created_by,
                 Date approved_at, String approved_by, Date checked_at, String checked_by,
                 Date updated_at, String updated_by, String warehouse_at){
        this.id = id;
        this.origin_id = origin_id;
        this.order_type = order_type;
        this.dest_id = dest_id;
        this.dest_type = dest_type;
        this.date = date;
        this.driver_id = driver_id;
        this.created_at = created_at;
        this.created_by = created_by;
        this.approved_at = approved_at;
        this.approved_by = approved_by;
        this.checked_at = checked_at;
        this.checked_by = checked_by;
        this.updated_at = updated_at;
        this.updated_by = updated_by;
        this.warehouse_at = warehouse_at;

    }

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

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getDest_id() {
        return dest_id;
    }

    public void setDest_id(String dest_id) {
        this.dest_id = dest_id;
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

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
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

    public String getChecked_by() {
        return checked_by;
    }

    public void setChecked_by(String checked_by) {
        this.checked_by = checked_by;
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

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
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

    public String getWarehouse_at() {
        return warehouse_at;
    }

    public void setWarehouse_at(String warehouse_at) {
        this.warehouse_at = warehouse_at;
    }
}
