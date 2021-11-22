package level.up.kaa_bar.controllers;

import level.up.kaa_bar.dto.AddDrinkForm;
import level.up.kaa_bar.model.Drink;
import level.up.kaa_bar.repo.DrinksRepo;
import level.up.kaa_bar.repo.DrinksRepoPaging;
import level.up.kaa_bar.utils.PaginationParams;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@Controller
@AllArgsConstructor
public class DrinkController  {

    final static String DRINKINFO = "drinkinfo";
    private DrinksRepo drinksRepo;

    @GetMapping("/addDrink")
    public String add(
            Model model,
            @ModelAttribute("form") AddDrinkForm form
                ){
        model.addAttribute("isAdded", false);
        return DRINKINFO;
    }


    @GetMapping("/drinks/modify/{id}")
    public String modify(@PathVariable("id") int id,
                         Model model) {
        Drink drink = drinksRepo.findById(id).orElseThrow(NoSuchElementException::new);
        AddDrinkForm form = new AddDrinkForm(drink.getName(), drink.getBrand(), drink.getPrice(),
                drink.getQuantity(), drink.getTyp());
        form.setId(id);
        model.addAttribute("isAdded", false);
        model.addAttribute("form", form);

        return DRINKINFO;
    }

    @PostMapping("/save")
    @Transactional
    public String save(Model model,
                       @Valid @ModelAttribute("form")AddDrinkForm form,
                       @RequestParam("id") int id,
                       BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {
            return DRINKINFO;
        }
        Drink drink;
        if (id==0) {
            drink = new Drink(
                    form.getName(),
                    form.getBrand(),
                    form.getPrice(),
                    form.getQuantity(),
                    form.getTyp());

        } else {
            drink = drinksRepo.findById(id).orElseThrow(NoSuchElementException::new);
            drink.setName(form.getName());
            drink.setBrand(form.getBrand());
            drink.setPrice(form.getPrice());
            drink.setQuantity(form.getQuantity());
            drink.setTyp(form.getTyp());


        }
        drinksRepo.save(drink);

        model.addAttribute("isAdded", true);

        return DRINKINFO;
    }

}
