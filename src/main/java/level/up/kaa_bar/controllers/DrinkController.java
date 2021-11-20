package level.up.kaa_bar.controllers;

import level.up.kaa_bar.model.Drink;
import level.up.kaa_bar.repo.DrinksRepo;
import level.up.kaa_bar.repo.DrinksRepoPaging;
import level.up.kaa_bar.utils.PaginationParams;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequestMapping({"/drinks", ""})
@AllArgsConstructor
public class DrinkController  {
    private DrinksRepoPaging drinksRepoPaging;
    private DrinksRepo drinksRepo;

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                        @RequestParam(value = "q", required = false, defaultValue = "") String query
//                      ,@RequestParam(defaultValue = "10") int count
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        String queryString = query.trim();

        Page<Drink> drinks = hasText(queryString) ?
                drinksRepoPaging.findContact(queryString, pageRequest)
                : drinksRepoPaging.findAll(pageRequest);

//        model.addAttribute("drinks", drinksRepo.findAll()
//        .stream().collect(Collectors.toList()));
        model.addAttribute("query", query);
        model.addAttribute("drinks", drinks.stream().collect(Collectors.toList()));
        model.addAttribute("title", "Lets drink!");

        PaginationParams<Drink> paginationParams = new PaginationParams<>(drinks);
        model.addAllAttributes(paginationParams.getParams(page));

        return "drinks";
    }

    @PostMapping
    @Transactional
    public @ResponseBody
    String create(@RequestParam("name") String name,
                  @RequestParam("brand") String brand,
                  @RequestParam("price") int price,
                  @RequestParam("quantity") int quantity,
                  @RequestParam("typ") String typ
    ) {
        Drink drink = new Drink();
        //Drink drink = new Drink();
        drink.setName(name);
        drink.setBrand(brand);
        drink.setPrice(price);
        drink.setQuantity(quantity);
        drink.setTyp(typ);

        drinksRepoPaging.save(drink);

        return "OK";
    }


//    @GetMapping("/drinks/{id}/modify")
//    public ModelAndView modify(@PathVariable(value = "id") int id, Model model) {
//        Option<Drink> drink = drinksRepo.findById(id);
//        ArrayList<Drink> res = new ArrayList<>();
//
//
//        if (drinksRepo.findById(id).isPresent()) {
//            Drink drinks = drinksRepo.findById(id).get();
//            drinks.se();
//            petsDAO.save(pets);
//            return new ModelAndView("redirect:/");
//        } else {
//            return new ModelAndView("redirect:/error");
//        }
//
//

//    }
@GetMapping("/drinks/{id}/modify")
public String modify(@PathVariable( value = "id") int id, Model model) {
        Optional<Drink> drinkOption = drinksRepo.findById(id);
        ArrayList<Drink> res = new ArrayList<>();
        drinkOption.ifPresent(res::add);
        model.addAttribute("drink", res);
        return "modify";
}
@PostMapping("/drinks/{id}/modify")
    public String modify(@PathVariable( value = "id") int id,
                         @RequestParam String name,
                         @RequestParam String brand,
                         @RequestParam int price,
                         @RequestParam int quantity,
                         @RequestParam String typ
        , Model model
){
        Drink drink = drinksRepo.findById(id).orElseThrow();
        drink.setName(name);
        drink.setBrand(brand);
        drink.setPrice(price);
        drink.setQuantity(quantity);
        drink.setTyp(typ);
        drinksRepo.save(drink);

        return "redirct:/";
    }


}
