package inventory.system.model;

public class DashboardCardSuperAdminModel {
    private int totalChecking;
    private String lastUpdateChecking;
    private int totalWaiting;
    private String lasUpdateWaiting;
    private int totalApproved;
    private String lastUpdateApproved;
    private int totalRejected;
    private String lastUpdateRejected;
    private int totalOrder;
    private String lastUpdateOrder;
    private int totalRetur;
    private String lastUpdateRetur;

    public DashboardCardSuperAdminModel(int totalChecking, String lastUpdateChecking, int totalWaiting, String lasUpdateWaiting, int totalApproved, String lastUpdateApproved, int totalRejected, String lastUpdateRejected, int totalOrder, String lastUpdateOrder, int totalRetur, String lastUpdateRetur) {
        this.totalChecking = totalChecking;
        this.lastUpdateChecking = lastUpdateChecking;
        this.totalWaiting = totalWaiting;
        this.lasUpdateWaiting = lasUpdateWaiting;
        this.totalApproved = totalApproved;
        this.lastUpdateApproved = lastUpdateApproved;
        this.totalRejected = totalRejected;
        this.lastUpdateRejected = lastUpdateRejected;
        this.totalOrder = totalOrder;
        this.lastUpdateOrder = lastUpdateOrder;
        this.totalRetur = totalRetur;
        this.lastUpdateRetur = lastUpdateRetur;
    }

    public DashboardCardSuperAdminModel() {
    }

    public int getTotalChecking() {
        return totalChecking;
    }

    public void setTotalChecking(int totalChecking) {
        this.totalChecking = totalChecking;
    }

    public String getLastUpdateChecking() {
        return lastUpdateChecking;
    }

    public void setLastUpdateChecking(String lastUpdateChecking) {
        this.lastUpdateChecking = lastUpdateChecking;
    }

    public int getTotalWaiting() {
        return totalWaiting;
    }

    public void setTotalWaiting(int totalWaiting) {
        this.totalWaiting = totalWaiting;
    }

    public String getLasUpdateWaiting() {
        return lasUpdateWaiting;
    }

    public void setLasUpdateWaiting(String lasUpdateWaiting) {
        this.lasUpdateWaiting = lasUpdateWaiting;
    }

    public int getTotalApproved() {
        return totalApproved;
    }

    public void setTotalApproved(int totalApproved) {
        this.totalApproved = totalApproved;
    }

    public String getLastUpdateApproved() {
        return lastUpdateApproved;
    }

    public void setLastUpdateApproved(String lastUpdateApproved) {
        this.lastUpdateApproved = lastUpdateApproved;
    }

    public int getTotalRejected() {
        return totalRejected;
    }

    public void setTotalRejected(int totalRejected) {
        this.totalRejected = totalRejected;
    }

    public String getLastUpdateRejected() {
        return lastUpdateRejected;
    }

    public void setLastUpdateRejected(String lastUpdateRejected) {
        this.lastUpdateRejected = lastUpdateRejected;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getLastUpdateOrder() {
        return lastUpdateOrder;
    }

    public void setLastUpdateOrder(String lastUpdateOrder) {
        this.lastUpdateOrder = lastUpdateOrder;
    }

    public int getTotalRetur() {
        return totalRetur;
    }

    public void setTotalRetur(int totalRetur) {
        this.totalRetur = totalRetur;
    }

    public String getLastUpdateRetur() {
        return lastUpdateRetur;
    }

    public void setLastUpdateRetur(String lastUpdateRetur) {
        this.lastUpdateRetur = lastUpdateRetur;
    }
}
