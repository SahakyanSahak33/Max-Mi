package sahak.sahakyan.maxmi.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    private int price;

    private String description;

    @Lob
    private byte[] image;

    // Constructors, getters, and setters
}