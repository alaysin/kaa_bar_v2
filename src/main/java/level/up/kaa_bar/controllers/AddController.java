package level.up.kaa_bar.controllers;

import level.up.kaa_bar.model.Drink;
import level.up.kaa_bar.repo.DrinksRepo;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AddController {

    @Autowired
    private DrinksRepo drinksRepo;

    @GetMapping("/add")
    public String viewAddForm(
            Model model,
            @ModelAttribute("form") Drink form,
            BindingResult bindingResult
    ) {
        model.addAttribute("form", form);
        model.addAttribute("bindingResult", bindingResult);
        return "add";
    }

    @PostMapping("/add")
    public String add(Model model,
                      @Valid @ModelAttribute("form") Drink form,
                      BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "add";
        }

        Drink added = drinksRepo.saveNewDrink(form.getName(), form.getBrand()
                    , form.getPrice(), form.getQuantity(), form.getTyp());

            //return "add";


//        model.addAttribute("name", added.getName());
//        model.addAttribute("brand", added.getBrand());
//        model.addAttribute("price", added.getPrice());
//        model.addAttribute("quantity", added.getQuantity());
//        model.addAttribute("typ", added.getTyp());
        return "/";
    }
}
