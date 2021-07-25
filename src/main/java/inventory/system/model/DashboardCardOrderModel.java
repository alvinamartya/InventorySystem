package inventory.system.model;

public class DashboardCardOrderModel {
    private int totalCheckingList;
    private String lastUpdateChecking;
    private int totalApproved;
    private String lastUpdateApproved;
    private int totalRejected;
    private String lastUpdateRejected;
    private int totalRetur;
    private String lastUpdateRetur;
    private int totalIn;
    private String lastUpdateIn;
    private int totalOut;
    private String lastUpdateOut;

    public DashboardCardOrderModel(int totalCheckingList, String lastUpdateChecking, int totalApproved, String lastUpdateApproved, int totalRejected, String lastUpdateRejected, int totalRetur, String lastUpdateRetur, int totalIn, String lastUpdateIn, int totalOut, String lastUpdateOut) {
        this.totalCheckingList = totalCheckingList;
        this.lastUpdateChecking = lastUpdateChecking;
        this.totalApproved = totalApproved;
        this.lastUpdateApproved = lastUpdateApproved;
        this.totalRejected = totalRejected;
        this.lastUpdateRejected = lastUpdateRejected;
        this.totalRetur = totalRetur;
        this.lastUpdateRetur = lastUpdateRetur;
        this.totalIn = totalIn;
        this.lastUpdateIn = lastUpdateIn;
        this.totalOut = totalOut;
        this.lastUpdateOut = lastUpdateOut;
    }

    public DashboardCardOrderModel() {
    }

    public int getTotalCheckingList() {
        return totalCheckingList;
    }

    public void setTotalCheckingList(int totalCheckingList) {
        this.totalCheckingList = totalCheckingList;
    }

    public String getLastUpdateChecking() {
        return lastUpdateChecking;
    }

    public void setLastUpdateChecking(String lastUpdateChecking) {
        this.lastUpdateChecking = lastUpdateChecking;
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

    public int getTotalIn() {
        return totalIn;
    }

    public void setTotalIn(int totalIn) {
        this.totalIn = totalIn;
    }

    public String getLastUpdateIn() {
        return lastUpdateIn;
    }

    public void setLastUpdateIn(String lastUpdateIn) {
        this.lastUpdateIn = lastUpdateIn;
    }

    public int getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(int totalOut) {
        this.totalOut = totalOut;
    }

    public String getLastUpdateOut() {
        return lastUpdateOut;
    }

    public void setLastUpdateOut(String lastUpdateOut) {
        this.lastUpdateOut = lastUpdateOut;
    }
}
