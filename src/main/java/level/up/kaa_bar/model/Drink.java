package level.up.kaa_bar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "drinks")
@NoArgsConstructor
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drinks_id_seq")
    @SequenceGenerator(name = "drinks_id_seq", allocationSize = 1)
    @Getter
    private int id;

    @Column(nullable = false, updatable = false, length = 50)
//    @NotBlank
    @Getter @Setter
    private String name;

    @Column(nullable = false, updatable = false, length = 50)
//    @NotBlank
    @Getter @Setter
    private String brand;

    @Column(nullable = false, updatable = false, length = 50)
//    @NotBlank
    @Getter @Setter
    private int price;

    @Column(nullable = false, updatable = true, length = 50)
    @Getter @Setter
    private int quantity;

    @Column(nullable = false, updatable = false, length = 50)
//    @NotBlank
    @Getter @Setter
    private String typ;

    public Drink(String name, String brand, int price, int quantity, String typ) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.typ = typ;
    }


}
