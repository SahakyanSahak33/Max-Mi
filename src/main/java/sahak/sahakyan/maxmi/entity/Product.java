package sahak.sahakyan.maxmi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "basket_products" ,
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "basket_id"))
    private List<Basket> baskets;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Transient
    private String base64Image;

    public void addBasket(Basket basket) {
        System.out.println("{--------------------| addBasket |---------------------}");
        System.out.println(baskets);
        if (baskets==null) {
            baskets = new ArrayList<>();
        }
        System.out.println(basket);
        baskets.add(basket);
    }
}