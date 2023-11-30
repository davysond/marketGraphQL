package com.project.marketGraphQL.modules.product;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @MutationMapping()
    ProductEntity addProduct(@Argument ProductInput productInput) {
        return this.productRepository
                .save(new ProductEntity(productInput.name, productInput.price, productInput.categoryId));
    }

    record ProductInput(String name, BigDecimal price, UUID categoryId) {
    }

    @QueryMapping
    Iterable<ProductEntity> products() {
        return this.productRepository.findAll();
    }
}
