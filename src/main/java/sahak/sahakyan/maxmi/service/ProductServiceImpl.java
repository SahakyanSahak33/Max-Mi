package sahak.sahakyan.maxmi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sahak.sahakyan.maxmi.dao.ProductRepository;
import sahak.sahakyan.maxmi.entity.Product;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public Product findByProductId(Long id) {
        return productRepository.findByProductId(id);
    }

    @Override
    public Product findByPriceBetween(int p1, int p2) {
        return productRepository.findByPriceBetween(p1, p2);
    }

    @Override
    public Product findByProductName(String name) {
        return productRepository.findByProductName(name);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(findByProductId(id));
    }

    @Override
    public void changeProductPrice(Long id, int price) {
        Product product = productRepository.findByProductId(id);
        product.setPrice(price);
        productRepository.save(product);
    }
}
