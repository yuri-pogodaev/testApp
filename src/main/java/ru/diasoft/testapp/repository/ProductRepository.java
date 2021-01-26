package ru.diasoft.testapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.diasoft.testapp.model.Product;

import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID > {
    @Query("select sum(p.amount) from Product p")
    int sum();
}
