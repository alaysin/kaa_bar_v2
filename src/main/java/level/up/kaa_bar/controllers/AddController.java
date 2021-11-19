package level.up.kaa_bar.controllers;

import level.up.kaa_bar.model.Drink;
import level.up.kaa_bar.repo.DrinksRepo;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AddController {

    @Autowired
    private DrinksRepo drinksRepo;

    @GetMapping("/add")
    public String viewAddForm(
            Model model,
            @ModelAttribute AddDrinkForm form,
            BindingResult bindingResult
    ) {
        model.addAttribute("form", form);
        model.addAttribute("bindingResult", bindingResult);
        return "add";
    }

    @PostMapping("/add")
    @Transactional
    public String add(Model model,
                      @Valid @ModelAttribute("form") AddDrinkForm form,
                      //@RequestParam
                      BindingResult bindingResult
    ) {
        model.addAttribute("form", form);
        model.addAttribute("bindingResult", bindingResult);

        if (bindingResult.hasErrors()) {
            return "add";
        }

        Drink added;

        try {
            added = drinksRepo.saveNewDrink(form.getName(), form.getBrand()
                    , form.getPrice(), form.getQuantity(), form.getTyp());
        } catch (ConstraintViolationException constraintViolationException) {
            bindingResult.addError(new FieldError("form",
                    "name", "name is not correct"));
            return "add";
    }

        model.addAttribute("name", added.getName());
        model.addAttribute("brand", added.getBrand());
        model.addAttribute("price", added.getPrice());
        model.addAttribute("quantity", added.getQuantity());
        model.addAttribute("typ", added.getTyp());
        return "added";
    }

    /*
    @PostMapping
    @Transactional
    public @ResponseBody String add(@RequestParam("name") String name,
                                    @RequestParam("brand") String brand,
                                    @RequestParam("price") int price,
                                    @RequestParam("quantity") int quantity,
                                    @RequestParam("typ") String typ) {
        Drink drink = new Drink();
        drink.setName(name);
        drink.setBrand(brand);
        drink.setPrice(price);
        drink.setQuantity(quantity);
        drink.setTyp(typ);

        drinksRepo.saveDrink(drink);
        return "OK!";
    }
    */
}
