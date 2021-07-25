package inventory.system.entity;

public class ReturInput {

    private String origin_warehouse_id;
    private String dest_warehouse_id;
    private String origin_type;
    private String dest_type;
    private Integer driver_id=0;
    private String detailJSON;

    public ReturInput() {
    }

    public ReturInput(String origin_type, String dest_type,
                      String origin_warehouse_id, String dest_warehouse_id) {
        this.origin_type = origin_type;
        this.dest_type = dest_type;
        this.origin_warehouse_id = origin_warehouse_id;
        this.dest_warehouse_id = dest_warehouse_id;
        this.driver_id = 0;
    }

    public String getDest_type() {
        return dest_type;
    }

    public void setDest_type(String dest_type) {
        this.dest_type = dest_type;
    }

    public String getOrigin_warehouse_id() {
        return origin_warehouse_id;
    }

    public void setOrigin_warehouse_id(String origin_warehouse_id) {
        this.origin_warehouse_id = origin_warehouse_id;
    }

    public String getDest_warehouse_id() {
        return dest_warehouse_id;
    }

    public void setDest_warehouse_id(String dest_warehouse_id) {
        this.dest_warehouse_id = dest_warehouse_id;
    }

    public String getOrigin_type() {
        return origin_type;
    }

    public void setOrigin_type(String origin_type) {
        this.origin_type = origin_type;
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public String getDetailJSON() {
        return detailJSON;
    }

    public void setDetailJSON(String detailJSON) {
        this.detailJSON = detailJSON;
    }
}
