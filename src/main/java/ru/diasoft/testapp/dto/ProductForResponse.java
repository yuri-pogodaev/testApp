package ru.diasoft.testapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.diasoft.testapp.model.Product;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductForResponse {
    private UUID id;
    private String name;
    private int amount;

    public ProductForResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.amount = product.getAmount();
    }
}
