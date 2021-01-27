package ru.diasoft.testapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.diasoft.testapp.dto.ProductForInit;
import ru.diasoft.testapp.dto.ProductForResponse;
import ru.diasoft.testapp.dto.ProductForUpdate;
import ru.diasoft.testapp.service.ProductService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UUID> save(@RequestBody @Valid ProductForInit productForInit) {
        UUID created = productService.init(productForInit);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(created).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) throws Exception {
        productService.deleteById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductForResponse getById(@PathVariable("id") UUID id) throws Exception {
        return productService.getById(id);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductForResponse> getAll() {
        return productService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid ProductForUpdate productForUpdate) throws Exception {
        productService.update(productForUpdate, id);
    }

    @GetMapping("/getSumAmountQuery/{name}")
    public int sum(@PathVariable("name") String name) {
        return productService.sum(name);
    }
}
