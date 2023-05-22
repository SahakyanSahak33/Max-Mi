package sahak.sahakyan.maxmi.entity;

import javax.persistence.*;

@Entity
@Table(name = "liked_products")
public class LikedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Constructors, getters, and setters
}