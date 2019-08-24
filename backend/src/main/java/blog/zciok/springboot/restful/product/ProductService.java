package blog.zciok.springboot.restful.product;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements Serializable {

	private static final long serialVersionUID = 6563372562747081329L;
	
	private final ProductRepository productRespository;

    public List<Product> findAll() {
        List<Product> products = productRespository.findAll(Sort.by("category").ascending().and(Sort.by("name").ascending()));
        return products;
    }

    public Optional<Product> findById(Long id) {
        return productRespository.findById(id);
    }

    public Product save(Product stock) {
        return productRespository.save(stock);
    }

    public void deleteById(Long id) {
        productRespository.deleteById(id);
    }

	public List<Product> findByCategory(String category) {
		return productRespository.findByCategorySortByName(category);
	}
	
	Page<Product> productPageable(Pageable pageable) {
		return productRespository.findAll(pageable);
	}   
	
	public Page<Product> categoryPageable(String category, Pageable pageable) {
		return productRespository.categoryPageable(category, pageable);
	}	
}
