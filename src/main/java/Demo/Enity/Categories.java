package Demo.Enity;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.util.List;

@Entity
@Table(name="categories")
public class Categories {
    @Id
    @Column(name="categories_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoriesId;

    @Column(name="categories_name")
    private String categoriesName;

    @Column(name="description")
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="products_categories",
            joinColumns = @JoinColumn(name="categories_id"),
            inverseJoinColumns=@JoinColumn(name="products_id")
    )
    private List<Products> products;

    public Categories(int categoriesId, String categoriesName, String description, List<Products> products) {
        this.categoriesId = categoriesId;
        this.categoriesName = categoriesName;
        this.description = description;
        this.products = products;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public Categories() {
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
