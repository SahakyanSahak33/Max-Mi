package sahak.sahakyan.maxmi.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sahak.sahakyan.maxmi.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByProductId(Long id);
    Product findByPriceBetween(int p1, int p2);
    Product findByProductName(String name);

    @Override
    @NotNull
    List<Product> findAll();
}
