package blog.zciok.springboot.restful.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductAPI implements Serializable {

	private static final long serialVersionUID = 5864381013389092576L;

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> findAll");
		return ResponseEntity.ok(productService.findAll());
	}

	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
		log.info(">>>>>>>>>>>>>>>>>>> create");
		return ResponseEntity.ok(productService.save(product));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Optional<Product> stock = productService.findById(id);
		if (!stock.isPresent()) {
			log.error("Id = " + id + " does not exist.");
			ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(stock.get());
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<Product>> findByCategory(@PathVariable String category) {
		return ResponseEntity.ok(productService.findByCategory(category));
	}

	@PutMapping("/category/{category}/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
		log.info(">>>>>>>>>>>>>>>>>>> update");		
		if (!productService.findById(id).isPresent()) {
			log.error("Id = " + id + " does not exist.");
			ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(productService.save(product));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!productService.findById(id).isPresent()) {
			log.error("Id = " + id + " does not exist.");
			ResponseEntity.badRequest().build();
		}
		productService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/productPageable")
	Page<Product> productPageable(Pageable pageable) {
		System.out.println(">>>>>>>>>>>>>>>>>>> pageable");
		return productService.productPageable(pageable);
	}
	
	// Examples:
	// http://localhost:8080/spring-boot-vuejs/products/tank/categoryPageable?page=0&size=2
	// http://localhost:8080/spring-boot-vuejs/products/bomber/categoryPageable?page=0&size=2
	// http://localhost:8080/spring-boot-vuejs/products/warship/categoryPageable?page=0&size=2	
	@GetMapping(value = "/{category}/categoryPageable")
	Page<Product> productByCategoryPageable(@PathVariable String category, Pageable pageable) {
		System.out.println(">>>>>>>>>>>>>>>>>>> category pageable");
		return productService.categoryPageable(category, pageable);
	}	

}
