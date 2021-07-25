package inventory.system.model;

import java.util.Date;

public class MonitoringReturModel {
    private String retur_id;
    private String product_name;
    private String origin_name;
    private String dest_name;
    private String origin_shelf;
    private String dest_shelf;
    private Date date;
    private int quantity;
    private String type;

    public MonitoringReturModel(String retur_id, String product_name, String origin_name, String dest_name, String origin_shelf, String dest_shelf, Date date, int quantity, String type) {
        this.retur_id = retur_id;
        this.product_name = product_name;
        this.origin_name = origin_name;
        this.dest_name = dest_name;
        this.origin_shelf = origin_shelf;
        this.dest_shelf = dest_shelf;
        this.date = date;
        this.quantity = quantity;
        this.type = type;
    }

    public MonitoringReturModel() {
    }

    public String getRetur_id() {
        return retur_id;
    }

    public void setRetur_id(String retur_id) {
        this.retur_id = retur_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public void setOrigin_name(String origin_name) {
        this.origin_name = origin_name;
    }

    public String getDest_name() {
        return dest_name;
    }

    public void setDest_name(String dest_name) {
        this.dest_name = dest_name;
    }

    public String getOrigin_shelf() {
        return origin_shelf;
    }

    public void setOrigin_shelf(String origin_shelf) {
        this.origin_shelf = origin_shelf;
    }

    public String getDest_shelf() {
        return dest_shelf;
    }

    public void setDest_shelf(String dest_shelf) {
        this.dest_shelf = dest_shelf;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
