package ua.spro.redisdemo.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.spro.redisdemo.entity.Product;
import ua.spro.redisdemo.repository.ProductDao;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

  private final ProductDao productDao;

  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    return ResponseEntity.ok(productDao.saveProduct(product));
  }

  @GetMapping
  public ResponseEntity<List<Product>> getProducts() {
    return ResponseEntity.ok(productDao.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable int id) {
    return ResponseEntity.ok(productDao.findById(id));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteProduct(@RequestParam int id) {
    productDao.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
