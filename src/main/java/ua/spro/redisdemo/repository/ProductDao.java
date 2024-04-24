package ua.spro.redisdemo.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ua.spro.redisdemo.entity.Product;

@Repository
@RequiredArgsConstructor
public class ProductDao {

  public static final String HASH_KEY = "Product";
  private final RedisTemplate<String, Object> redisTemplate;

  public Product saveProduct(Product product) {
    redisTemplate.opsForHash().put(HASH_KEY, product.getId() + "", product);
    return product;
  }

  public List<Product> findAll() {
    return redisTemplate.opsForHash().values(HASH_KEY).stream().map(o -> (Product) o).toList();
  }

  public Product findById(int id) {
    return (Product) redisTemplate.opsForHash().get(HASH_KEY, id + "");
  }

  public void deleteById(int id) {
    redisTemplate.opsForHash().delete(HASH_KEY, id + "");
  }
}
