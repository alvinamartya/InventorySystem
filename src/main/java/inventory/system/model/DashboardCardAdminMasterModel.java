package inventory.system.model;

public class DashboardCardAdminMasterModel {
    private int totalProductCategory;
    private String lastUpdateProductCategory;
    private int totalProduct;
    private String lastUpdateProduct;
    private int totalShelf;
    private String lastUpdateShelf;
    private int totalStore;
    private String lastUpdateStore;
    private int totalSupplier;
    private String lastUpdateSupplier;
    private int totalWarehouse;
    private String lastUpdateWarehouse;

    public DashboardCardAdminMasterModel(int totalProductCategory, String lastUpdateProductCategory, int totalProduct, String lastUpdateProduct, int totalShelf, String lastUpdateShelf, int totalStore, String lastUpdateStore, int totalSupplier, String lastUpdateSupplier, int totalWarehouse, String lastUpdateWarehouse) {
        this.totalProductCategory = totalProductCategory;
        this.lastUpdateProductCategory = lastUpdateProductCategory;
        this.totalProduct = totalProduct;
        this.lastUpdateProduct = lastUpdateProduct;
        this.totalShelf = totalShelf;
        this.lastUpdateShelf = lastUpdateShelf;
        this.totalStore = totalStore;
        this.lastUpdateStore = lastUpdateStore;
        this.totalSupplier = totalSupplier;
        this.lastUpdateSupplier = lastUpdateSupplier;
        this.totalWarehouse = totalWarehouse;
        this.lastUpdateWarehouse = lastUpdateWarehouse;
    }

    public DashboardCardAdminMasterModel() {
    }

    public int getTotalProductCategory() {
        return totalProductCategory;
    }

    public void setTotalProductCategory(int totalProductCategory) {
        this.totalProductCategory = totalProductCategory;
    }

    public String getLastUpdateProductCategory() {
        return lastUpdateProductCategory;
    }

    public void setLastUpdateProductCategory(String lastUpdateProductCategory) {
        this.lastUpdateProductCategory = lastUpdateProductCategory;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public String getLastUpdateProduct() {
        return lastUpdateProduct;
    }

    public void setLastUpdateProduct(String lastUpdateProduct) {
        this.lastUpdateProduct = lastUpdateProduct;
    }

    public int getTotalShelf() {
        return totalShelf;
    }

    public void setTotalShelf(int totalShelf) {
        this.totalShelf = totalShelf;
    }

    public String getLastUpdateShelf() {
        return lastUpdateShelf;
    }

    public void setLastUpdateShelf(String lastUpdateShelf) {
        this.lastUpdateShelf = lastUpdateShelf;
    }

    public int getTotalStore() {
        return totalStore;
    }

    public void setTotalStore(int totalStore) {
        this.totalStore = totalStore;
    }

    public String getLastUpdateStore() {
        return lastUpdateStore;
    }

    public void setLastUpdateStore(String lastUpdateStore) {
        this.lastUpdateStore = lastUpdateStore;
    }

    public int getTotalSupplier() {
        return totalSupplier;
    }

    public void setTotalSupplier(int totalSupplier) {
        this.totalSupplier = totalSupplier;
    }

    public String getLastUpdateSupplier() {
        return lastUpdateSupplier;
    }

    public void setLastUpdateSupplier(String lastUpdateSupplier) {
        this.lastUpdateSupplier = lastUpdateSupplier;
    }

    public int getTotalWarehouse() {
        return totalWarehouse;
    }

    public void setTotalWarehouse(int totalWarehouse) {
        this.totalWarehouse = totalWarehouse;
    }

    public String getLastUpdateWarehouse() {
        return lastUpdateWarehouse;
    }

    public void setLastUpdateWarehouse(String lastUpdateWarehouse) {
        this.lastUpdateWarehouse = lastUpdateWarehouse;
    }
}
