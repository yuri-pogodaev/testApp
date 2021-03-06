package ru.diasoft.testapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.testapp.dto.ProductForInit;
import ru.diasoft.testapp.dto.ProductForResponse;
import ru.diasoft.testapp.dto.ProductForUpdate;
import ru.diasoft.testapp.exception.EntityNotFoundException;
import ru.diasoft.testapp.model.Product;
import ru.diasoft.testapp.repository.ProductRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createNewProduct(@Valid ProductForInit productForInit) {
        Product newProduct = Product.builder()
                .amount(productForInit.getAmount())
                .name(productForInit.getName()).build();
        productRepository.saveAndFlush(newProduct);
        return newProduct;
    }

    @Transactional
    public UUID init(@Valid ProductForInit productForInit) {
        Product newProduct = createNewProduct(productForInit);
        return newProduct.getId();
    }

    @Transactional
    public void deleteById(UUID id) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("product not found", id));
        productRepository.deleteById(product.getId());
    }

    @Transactional
    public ProductForResponse getById(UUID id) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("product not found", id));
        return new ProductForResponse(product);
    }

    @Transactional
    public List<ProductForResponse> getAll() {
        List<Product> list = productRepository.findAll();
        return list.stream().map(ProductForResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public void update(@Valid ProductForUpdate productForUpdate, UUID id) throws Exception {
        productRepository.findById(id).map(u -> {
            u.setName(productForUpdate.getName());
            u.setAmount(productForUpdate.getAmount());
            return productRepository.saveAndFlush(u);
        });
    }

    @Transactional
    public int sum(String name) {
        return productRepository.sum(name);
    }
}
