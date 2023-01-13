package pl.home.helper;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.home.helper.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
