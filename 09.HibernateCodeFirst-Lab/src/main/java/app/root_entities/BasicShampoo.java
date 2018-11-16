package app.root_entities;

import app.contracts.Shampoo;
import app.enums.Size;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public class BasicShampoo implements Shampoo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String brand;

    @Basic
    private BigDecimal price;

    @Enumerated
    private Size size;

    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "label", referencedColumnName = "id")
    private BasicLabel label;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    public BasicShampoo() {
        this.setIngredients(new HashSet<>());
    }

    public BasicShampoo(String brand, BigDecimal price, Size size, BasicLabel label) {
        this();
        this.setBrand(brand);
        this.setPrice(price);
        this.setSize(size);
        this.setLabel(label);
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Size getSize() {
        return this.size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public BasicLabel getLabel() {
        return this.label;
    }

    @Override
    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    @Override
    public Set<BasicIngredient> getIngredients() {
        return new HashSet<>(this.ingredients);
    }

    @Override
    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public void addIngredient(BasicIngredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
