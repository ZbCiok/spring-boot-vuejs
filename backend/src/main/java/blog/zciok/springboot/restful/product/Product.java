package blog.zciok.springboot.restful.product;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Product implements Serializable {

	private static final long serialVersionUID = 4013720867658671288L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private String category;
    
    private String description;
    
    private String country;    
    
    private String image;
}
