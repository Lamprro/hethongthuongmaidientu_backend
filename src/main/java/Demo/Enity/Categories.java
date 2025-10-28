package Demo.Enity;

import jakarta.persistence.*;
import org.w3c.dom.Text;

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

    public Categories(int categoriesId, String categoriesName, String description) {
        this.categoriesId = categoriesId;
        this.categoriesName = categoriesName;
        this.description = description;
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
