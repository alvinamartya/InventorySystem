package inventory.system.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shelf")
public class Shelf {

    @Id
    private String id;

    private String warehouse_id;

    private String type_shelf;

    private String product_category_id;

    private int rows_shelf;

    private int columns_shelf;

    private int quantity_shelf;

    private int is_empty;

    private Date created_at;
    private String created_by;
    private Date updated_at;
    private String updated_by;

    public int getIs_empty() {
        return is_empty;
    }

    public void setIs_empty(int is_empty) {
        this.is_empty = is_empty;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Warehouses warehousesList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductCategory categoryList;

    public Warehouses getWarehousesList() {
        return warehousesList;
    }

    public void setWarehousesList(Warehouses warehousesList) {
        this.warehousesList = warehousesList;
    }

    public ProductCategory getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ProductCategory categoryList) {
        this.categoryList = categoryList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getType_shelf() {
        return type_shelf;
    }

    public void setType_shelf(String type_shelf) {
        this.type_shelf = type_shelf;
    }

    public String getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(String product_category_id) {
        this.product_category_id = product_category_id;
    }

    public int getRows_shelf() {
        return rows_shelf;
    }

    public void setRows_shelf(int rows_shelf) {
        this.rows_shelf = rows_shelf;
    }

    public int getColumns_shelf() {
        return columns_shelf;
    }

    public void setColumns_shelf(int columns_shelf) {
        this.columns_shelf = columns_shelf;
    }

    public int getQuantity_shelf() {
        return quantity_shelf;
    }

    public void setQuantity_shelf(int quantity_shelf) {
        this.quantity_shelf = quantity_shelf;
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
}
