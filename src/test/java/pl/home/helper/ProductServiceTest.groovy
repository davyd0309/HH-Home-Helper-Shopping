package pl.home.helper

import pl.home.helper.models.Product
import pl.home.helper.models.ProductDto
import spock.lang.Specification

class ProductServiceTest extends Specification {

    def productRepository = Mock(ProductRepository)
    def productService = new ProductService(productRepository)

    def "should create new product"() {
        given:
        def savedProduct = Product.builder()
                .id(UUID.randomUUID())
                .name("orange")
                .quantity(12)
                .build()

        when:
        def createdProductDto = productService.createProduct(new ProductDto("orange", 12))

        then:
        1 * productRepository.save(_ as Product) >> savedProduct
        createdProductDto.name() == "orange"
        createdProductDto.quantity() == 12
    }

    def "should get all products"() {
        given:
        productRepository.findAll() >> List.of(
                createProduct(),
                createProduct(),
                createProduct(),
                createProduct(),
                createProduct())
        when:
        def allProducts = productService.getAllProducts()
        then:
        allProducts.size() == 5
    }

    private Product createProduct() {
        Product.builder().id(UUID.randomUUID()).build()
    }
}
