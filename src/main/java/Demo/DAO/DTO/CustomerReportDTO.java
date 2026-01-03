package Demo.DAO.DTO;

import Demo.Enity.Users;

public class CustomerReportDTO {
    private Users users;
    private Long totalOrders;
    private Double totalSpent;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public CustomerReportDTO() {
    }

    public CustomerReportDTO(Users users, Long totalOrders, Double totalSpent) {
        this.users = users;
        this.totalOrders = totalOrders;
        this.totalSpent = totalSpent;
    }
}
