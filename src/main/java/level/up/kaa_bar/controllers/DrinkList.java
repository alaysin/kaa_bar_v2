package level.up.kaa_bar.controllers;

import level.up.kaa_bar.model.Drink;
import level.up.kaa_bar.repo.DrinksRepo;
import level.up.kaa_bar.repo.DrinksRepoPaging;
import level.up.kaa_bar.utils.PaginationParams;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequestMapping({"/drinks", ""})
@AllArgsConstructor
public class DrinkList {
    private DrinksRepoPaging drinksRepoPaging;
    private DrinksRepo drinksRepo;

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                        @RequestParam(value = "q", required = false, defaultValue = "") String query,
                        Authentication authentication
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
        model.addAttribute("isLoggedIn", authentication != null);

        PaginationParams<Drink> paginationParams = new PaginationParams<>(drinks);
        model.addAllAttributes(paginationParams.getParams(page));

        return "drinks";
    }

    @GetMapping("/drinks/delete/{id}")
    public String delete(
            @PathVariable("id") int id,
            Model model
    ){
        Drink drink = drinksRepo.findById(id).orElseThrow(NoSuchElementException::new);
        drinksRepo.delete(drink);

        return "redirect:/";
    }
}
