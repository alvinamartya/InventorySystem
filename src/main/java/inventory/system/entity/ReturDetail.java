package inventory.system.entity;


import javax.persistence.*;

@Entity
@Table(name = "retur_details")
public class ReturDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String retur_id;
    private Integer product_id;
    private Integer qty;
    private String origin_shelf_id;
    private String dest_shelf_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "retur_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Retur returList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product productList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dest_shelf_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Shelf destshelfList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "origin_shelf_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Shelf originshelfList;

    public ReturDetail(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRetur_id() {
        return retur_id;
    }

    public void setRetur_id(String retur_id) {
        this.retur_id = retur_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getOrigin_shelf_id() {
        return origin_shelf_id;
    }

    public void setOrigin_shelf_id(String origin_shelf_id) {
        this.origin_shelf_id = origin_shelf_id;
    }

    public String getDest_shelf_id() {
        return dest_shelf_id;
    }

    public void setDest_shelf_id(String dest_shelf_id) {
        this.dest_shelf_id = dest_shelf_id;
    }

    public Product getProductList() {
        return productList;
    }

    public void setProductList(Product productList) {
        this.productList = productList;
    }

    public Retur getReturList() {
        return returList;
    }

    public void setReturList(Retur returList) {
        this.returList = returList;
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
