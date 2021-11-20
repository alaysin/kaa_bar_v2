package level.up.kaa_bar.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


public class AddDrinkForm {

    @Getter @Setter
    @Length(max = 50)
    private String name;

    @Getter @Setter
    @Length(max = 50)
    private String brand;

    @Getter @Setter
    @PositiveOrZero
    private int price;

    @Getter @Setter
    @PositiveOrZero
    private int quantity;

    @Getter @Setter
    @Length(max = 50)
    private String typ;

    public AddDrinkForm() {
    }

    public AddDrinkForm(@Length(max = 50) String name,
                        @Length(max = 50) String brand,
                        @PositiveOrZero int price,
                        @PositiveOrZero int quantity,
                        @Length(max = 50) String typ) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.typ = typ;
    }
}
