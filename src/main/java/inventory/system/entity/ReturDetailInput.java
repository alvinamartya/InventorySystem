package inventory.system.entity;

public class ReturDetailInput {

    private Integer productID;
    private String productOrigin;
    private String productDest;
    private Integer productQty;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductOrigin() {
        return productOrigin;
    }

    public void setProductOrigin(String productOrigin) {
        this.productOrigin = productOrigin;
    }

    public String getProductDest() {
        return productDest;
    }

    public void setProductDest(String productDest) {
        this.productDest = productDest;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }
}
