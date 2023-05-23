package sahak.sahakyan.maxmi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "liked_products")
public class LikedProduct {

    @EmbeddedId
    private LikedProductId id = new LikedProductId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") // Maps the user_id attribute in the embedded ID
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId") // Maps the product_id attribute in the embedded ID
    @JoinColumn(name = "product_id")
    private Product product;
}

