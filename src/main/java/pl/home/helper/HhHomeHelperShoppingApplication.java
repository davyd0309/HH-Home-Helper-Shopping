package pl.home.helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.home.helper.models.Product;

@SpringBootApplication
public class HhHomeHelperShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HhHomeHelperShoppingApplication.class, args);
    }

}
