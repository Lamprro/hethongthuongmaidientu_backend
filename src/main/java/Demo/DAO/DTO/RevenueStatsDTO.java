package Demo.DAO.DTO;

import Demo.Enity.Orders;

import java.util.List;

public class RevenueStatsDTO {
    private double totalRevenue;
    private long ordersCount;
    private List<Orders> details;

    public RevenueStatsDTO(double totalRevenue, long ordersCount, List<Orders> details) {
        this.totalRevenue = totalRevenue;
        this.ordersCount = ordersCount;
        this.details = details;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(long ordersCount) {
        this.ordersCount = ordersCount;
    }

    public List<Orders> getDetails() {
        return details;
    }

    public void setDetails(List<Orders> details) {
        this.details = details;
    }

    public RevenueStatsDTO() {
    }
}
