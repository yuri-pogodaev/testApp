package ru.diasoft.testapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity
@Access(AccessType.FIELD)
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String name;
    private int amount;

}
