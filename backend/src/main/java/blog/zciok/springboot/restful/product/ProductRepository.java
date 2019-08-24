package blog.zciok.springboot.restful.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
    @Query("select c from Product c where c.category = :category order by c.name")
    List<Product> findByCategorySortByName(@Param("category") String category);	

    @Query(value = "SELECT * FROM Product WHERE category=?1 ORDER BY name", countQuery = "SELECT count(*) FROM Product WHERE category=?1", nativeQuery = true)
    Page<Product> categoryPageable(String category, Pageable pageable);    
}
