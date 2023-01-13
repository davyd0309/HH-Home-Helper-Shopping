package pl.home.helper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient;
import pl.home.helper.models.ProductDto
import reactor.test.StepVerifier
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
class IntegrationTest extends Specification {

    @Autowired
    private WebTestClient webTestClient;

    def "should return all products from database"() {
        expect: "Status is 200 and the response is not empty"
        var cardFlux = webTestClient.get()
                .uri("product")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(ProductDto.class)
                .getResponseBody()
        // then
        StepVerifier
                .create(cardFlux)
                .expectNext()
                .expectNextMatches(product -> {
                    product.name() == "asdasd"
                    product.quantity() == 100
                })
                .expectNext()
                .expectNextMatches(product -> {
                    product.name() == "fgfgf"
                    product.quantity() == 222
                })
                .expectComplete()
                .verify();
    }
}