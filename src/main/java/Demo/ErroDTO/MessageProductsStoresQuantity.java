package Demo.ErroDTO;

public class MessageProductsStoresQuantity {
    private String message;
    private int ordersId;
    private int errorProductsStoresId;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public int getErrorProductsStoresId() {
        return errorProductsStoresId;
    }

    public void setErrorProductsStoresId(int errorProductsStoresId) {
        this.errorProductsStoresId = errorProductsStoresId;
    }

    public MessageProductsStoresQuantity() {
    }

    public MessageProductsStoresQuantity(String message, int ordersId, int errorProductsStoresId) {
        this.message = message;
        this.ordersId = ordersId;
        this.errorProductsStoresId = errorProductsStoresId;
    }
}
