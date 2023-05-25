package sahak.sahakyan.maxmi.service;

import sahak.sahakyan.maxmi.entity.Product;

import java.util.List;

public interface ProductService {
    Product findByProductId(Long id);
    Product findByPriceBetween(int p1, int p2);
    Product findByProductName(String name);
    List<Product> findAll();
    void saveProduct(Product product);
    void deleteProduct(Long id);
    void changeProductPrice(Long id, int price);
}
