package inventory.system.model;

import inventory.system.entity.Product;

import javax.persistence.*;
import java.util.Date;

public class ShelfDetailCustomModel {

    private Integer id;

    private String shelf_id;
    private Integer product_id;

    private Date expired_at;
    private int row_shelf;
    private int col_shelf;

}
