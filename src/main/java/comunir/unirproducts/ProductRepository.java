package comunir.unirproducts;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;

public interface ProductRepository extends JpaRepository<Product, long> {
    List<Product> findByName(String name);
}
