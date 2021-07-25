package inventory.system.model;

public class DashboardChartModel {
    private String date;
    private int totalOrder;

    public DashboardChartModel(String date, int totalOrder) {
        this.date = date;
        this.totalOrder = totalOrder;
    }

    public DashboardChartModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }
}
