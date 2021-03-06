package level.up.kaa_bar.repo;

import level.up.kaa_bar.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrinksRepo extends JpaRepository<Drink, Integer> {

    @Query(name ="findByName")
    public Drink findByName(String name);

    @Query(name ="findByBrand")
    public List<Drink> findByBrand(String brand);

//    @Query(name = "existsById")
//    public boolean existsById(int id);

    @Query(name ="findByBrandAndAndTyp")
    public Drink findByBrandAndAndTyp(String brand, String typ);

    @Query(name ="findByPrice")
    public List<Drink> findByPrice(int price);

    public default Drink saveNewDrink(String drinkName, String brand, int price, int quantity, String typ) {
        Drink newDrink = new Drink(drinkName, brand, price, quantity, typ);
        save(newDrink);
        return newDrink;
    }
    public default Drink saveDrink(Drink drink){
        save(drink);
        return drink;
    }
}
