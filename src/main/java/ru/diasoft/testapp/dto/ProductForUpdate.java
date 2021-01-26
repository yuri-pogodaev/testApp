package ru.diasoft.testapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.diasoft.testapp.model.Product;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductForUpdate {
    private String name;
    private int amount;

    public ProductForUpdate(Product product) {
        this.name = product.getName();
        this.amount = product.getAmount();
    }
}

