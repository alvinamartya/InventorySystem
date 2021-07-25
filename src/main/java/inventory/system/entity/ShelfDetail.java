package inventory.system.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shelf_details")
public class ShelfDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String shelf_id;
    private Integer product_id;

    private Date expired_at;
    @Column(name="row_shelf")
    private int row_shelf;
    @Column(name="col_shelf")
    private int col_shelf;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product productList;

    public Product getProductList() {
        return productList;
    }

    public void setProductList(Product productList) {
        this.productList = productList;
    }

    public ShelfDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Date getExpired_at() {
        return expired_at;
    }

    public void setExpired_at(Date expired_at) {
        this.expired_at = expired_at;
    }

    public int getRow_shelf() {
        return row_shelf;
    }

    public void setRow_shelf(int row_shelf) {
        this.row_shelf = row_shelf;
    }

    public int getCol_shelf() {
        return col_shelf;
    }

    public void setCol_shelf(int col_shelf) {
        this.col_shelf = col_shelf;
    }

    public String getShelf_id() {
        return shelf_id;
    }

    public void setShelf_id(String shelf_id) {
        this.shelf_id = shelf_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public ShelfDetail(Integer id, String shelf_id, Integer product_id, Date expired_at, int row_shelf, int col_shelf) {
        this.id = id;
        this.shelf_id = shelf_id;
        this.product_id = product_id;
        this.expired_at = expired_at;
        this.row_shelf = row_shelf;
        this.col_shelf = col_shelf;
    }
}
