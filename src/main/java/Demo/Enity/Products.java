package Demo.Enity;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.util.List;

@Entity
@Table(name="products")
public class Products {
    @Id
    @Column(name="products_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productsId;

    @Column(name="products_name")
    private String productsName;

    @Column(name="description")
    private String description;

    @ManyToMany()
    @JoinTable(
            name="products_categories",
            joinColumns = @JoinColumn(name="products_id"),
            inverseJoinColumns=@JoinColumn(name="categories_id")
    )
    private List<Categories> categories;

    public Products(int productsId, String productsName, String description, List<Categories> categories) {
        this.productsId = productsId;
        this.productsName = productsName;
        this.description = description;
        this.categories = categories;
    }

    public Products() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
