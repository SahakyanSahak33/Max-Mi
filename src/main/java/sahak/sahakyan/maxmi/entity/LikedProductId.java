package sahak.sahakyan.maxmi.entity;

import javax.persistence.Column;
import java.io.Serializable;

public class LikedProductId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long product_id;
}
