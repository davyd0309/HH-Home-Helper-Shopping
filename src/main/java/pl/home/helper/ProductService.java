package pl.home.helper;

import pl.home.helper.models.Product;
import pl.home.helper.models.ProductDto;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(ProductDto productDto) {
        var savedProduct = productRepository.save(Product.builder()
                .name(productDto.name())
                .quantity(productDto.quantity())
                .build());
        return new ProductDto(savedProduct.getName(), savedProduct.getQuantity());
    }

    public List<ProductDto> getAllProducts() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(productRepository.findAll().iterator(), Spliterator.ORDERED), false)
                .map(product -> new ProductDto(product.getName(), product.getQuantity()))
                .toList();
    }

}
