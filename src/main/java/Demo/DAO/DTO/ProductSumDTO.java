package Demo.DAO.DTO;

public class ProductSumDTO {
    private int productsId;
    private String productsName;
    private Long total;

    public ProductSumDTO(int productsId, String productsName, Long total) {
        this.productsId = productsId;
        this.productsName = productsName;
        this.total = total;
    }

    public int getProductsId() {
        return productsId;
    }

    public void setProductsId(int productsId) {
        this.productsId = productsId;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
