package ru.diasoft.testapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.testapp.model.Product;

import java.util.UUID;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("select sum(p.amount) from Product p where p.name =:name")
    int sum(String name);
}
