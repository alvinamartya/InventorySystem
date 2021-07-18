package inventory.system.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String order_id;
    private Integer product_id;
    private String dest_shelf_id;
    private String origin_shelf_id;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Order orderList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product productList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dest_shelf_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Shelf destshelfList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "origin_shelf_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Shelf originshelfList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getDest_shelf_id() {
        return dest_shelf_id;
    }

    public void setDest_shelf_id(String dest_shelf_id) {
        this.dest_shelf_id = dest_shelf_id;
    }

    public String getOrigin_shelf_id() {
        return origin_shelf_id;
    }

    public void setOrigin_shelf_id(String origin_shelf_id) {
        this.origin_shelf_id = origin_shelf_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrderList() {
        return orderList;
    }

    public void setOrderList(Order orderList) {
        this.orderList = orderList;
    }

    public Product getProductList() {
        return productList;
    }

    public void setProductList(Product productList) {
        this.productList = productList;
    }

    public Shelf getDestshelfList() {
        return destshelfList;
    }

    public void setDestshelfList(Shelf destshelfList) {
        this.destshelfList = destshelfList;
    }

    public Shelf getOriginshelfList() {
        return originshelfList;
    }

    public void setOriginshelfList(Shelf originshelfList) {
        this.originshelfList = originshelfList;
    }
}
