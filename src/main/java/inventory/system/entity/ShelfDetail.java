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
    private String product_id;

    private Date expired_at;
    @Column(name="row_shelf")
    private int row_shelf;
    @Column(name="col_shelf")
    private int col_shelf;

    public ShelfDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShelf_id() {
        return shelf_id;
    }

    public void setShelf_id(String shelf_id) {
        this.shelf_id = shelf_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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
}
