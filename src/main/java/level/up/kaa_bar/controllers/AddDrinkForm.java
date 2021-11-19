package level.up.kaa_bar.controllers;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;


public class AddDrinkForm {

    @NotBlank
    @Getter @Setter
    @Length(max = 50)
    private String name;

    @NotBlank
    @Getter @Setter
    @Length(max = 50)
    private String brand;

    @NotBlank
    @Getter @Setter
    @Length(max = 50)
    private int price;

    @NotBlank
    @Getter @Setter
    @Length(max = 50)
    private int quantity;

    @NotBlank
    @Getter @Setter
    @Length(max = 50)
    private String typ;

    public AddDrinkForm() {
    }

    public AddDrinkForm(@NotBlank @Length(max = 50) String name,
                        @NotBlank @Length(max = 50) String brand,
                        @NotBlank @Length(max = 50) int price,
                        @NotBlank @Length(max = 50) int quantity,
                        @NotBlank @Length(max = 50) String typ) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.typ = typ;
    }
}
